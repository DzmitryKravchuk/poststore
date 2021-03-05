package com.itacademy.jd2.dk.poststore.web.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.itacademy.jd2.dk.poststore.dao.api.entity.enums.ShippingType;
import com.itacademy.jd2.dk.poststore.dao.api.entity.enums.Status;
import com.itacademy.jd2.dk.poststore.dao.api.entity.enums.StoreType;
import com.itacademy.jd2.dk.poststore.dao.api.entity.table.IInventory;
import com.itacademy.jd2.dk.poststore.dao.api.entity.table.IOrderItem;
import com.itacademy.jd2.dk.poststore.dao.api.entity.table.IOrderProduct;
import com.itacademy.jd2.dk.poststore.dao.api.entity.table.IProduct;
import com.itacademy.jd2.dk.poststore.dao.api.entity.table.IStore;
import com.itacademy.jd2.dk.poststore.dao.api.entity.table.IUserAccount;
import com.itacademy.jd2.dk.poststore.dao.api.filter.OrderItemFilter;
import com.itacademy.jd2.dk.poststore.dao.api.filter.ProductFilter;
import com.itacademy.jd2.dk.poststore.service.IInventoryService;
import com.itacademy.jd2.dk.poststore.service.IOrderItemService;
import com.itacademy.jd2.dk.poststore.service.IOrderProductService;
import com.itacademy.jd2.dk.poststore.service.IProductService;
import com.itacademy.jd2.dk.poststore.service.IStoreService;
import com.itacademy.jd2.dk.poststore.service.IUserAccountService;
import com.itacademy.jd2.dk.poststore.web.converter.OrderItemFromDTOConverter;
import com.itacademy.jd2.dk.poststore.web.converter.OrderItemToDTOConverter;
import com.itacademy.jd2.dk.poststore.web.converter.OrderProductFromDTOConverter;
import com.itacademy.jd2.dk.poststore.web.converter.ProductToDTOConverter;
import com.itacademy.jd2.dk.poststore.web.dto.OrderItemDTO;
import com.itacademy.jd2.dk.poststore.web.dto.OrderProductDTO;
import com.itacademy.jd2.dk.poststore.web.dto.ProductDTO;
import com.itacademy.jd2.dk.poststore.web.dto.grid.GridStateDTO;
import com.itacademy.jd2.dk.poststore.web.security.AuthHelper;

@Controller
@RequestMapping(value = "/orderProduct")
public class OrderProductController extends AbstractController {

	@Autowired
	private IProductService productService;
	@Autowired
	private ProductToDTOConverter productToDtoConverter;
	@Autowired
	private IOrderItemService orderItemService;
	@Autowired
	private OrderItemFromDTOConverter orderItemFromDtoConverter;
	@Autowired
	private OrderItemToDTOConverter orderItemToDtoConverter;

	@Autowired
	private IInventoryService inventoryService;
	@Autowired
	private IStoreService storeService;

	@Autowired
	private OrderProductFromDTOConverter orderProductFromDtoConverter;

	@Autowired
	private IUserAccountService userAccountService;

	@Autowired
	private IOrderProductService orderProductService;

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView index(final HttpServletRequest req,
			@RequestParam(name = "page", required = false) final Integer pageNumber,
			@RequestParam(name = "sort", required = false) final String sortColumn) {

		final GridStateDTO gridState = getListDTO(req);
		gridState.setPage(pageNumber);
		gridState.setSort(sortColumn, "updated");

		final ProductFilter filter = new ProductFilter();
		prepareFilter(gridState, filter);
		gridState.setTotalCount(productService.getCount(filter));

		final List<IProduct> entities = productService.find(filter);
		List<ProductDTO> dtos = entities.stream().map(productToDtoConverter).collect(Collectors.toList());

		final Map<String, Object> models = new HashMap<>();
		models.put("gridItems", dtos);
		return new ModelAndView("orderProduct.list", models);
	}

	@RequestMapping(value = "/{id}/add", method = RequestMethod.GET)
	public ModelAndView showForm(@PathVariable(name = "id", required = true) final Integer id) {
		Integer userAccountId = AuthHelper.getLoggedUserId();
		final IUserAccount userAccount = userAccountService.get(userAccountId);
		IOrderProduct orderProduct = orderProductService.getProductCart(userAccount);
		final Integer orderProductId = orderProduct.getId();
		final String productName = productService.get(id).getName();
		final Double productPrice = productService.get(id).getPrice();

		final OrderItemDTO dto = new OrderItemDTO();
		dto.setProductId(id);
		dto.setProductName(productName);
		dto.setProductPrice(productPrice);
		dto.setQuantity(1);
		dto.setOrderProductId(orderProductId);
		final Map<String, Object> hashMap = new HashMap<>();

		hashMap.put("formModel", dto);
		return new ModelAndView("orderProduct.item", hashMap);
	}

	@RequestMapping(value = "/order", method = RequestMethod.GET)
	public ModelAndView showOrder() {
		Integer userAccountId = AuthHelper.getLoggedUserId();
		final IUserAccount userAccount = userAccountService.get(userAccountId);
		IOrderProduct orderProduct = orderProductService.getProductCart(userAccount);

		// get Cost;
		Double actualCost = 0.0;
		final OrderItemFilter filter = new OrderItemFilter();
		filter.setFetchProduct(true);
		filter.setFetchOrderProduct(true);
		filter.setOrderProductId(orderProduct.getId());
		final List<IOrderItem> orderItems = orderItemService.find(filter);
		for (IOrderItem orderItem : orderItems) {
			actualCost = actualCost + (orderItem.getQuantity() * orderItem.getProduct().getPrice());
		}
		actualCost = Math.round(actualCost * 100.0) / 100.0;

		final Map<String, Object> hashMap = new HashMap<>();
		final OrderProductDTO dto = new OrderProductDTO();
		dto.setId(orderProduct.getId());
		dto.setUserAccountId(userAccountId);
		dto.setStatus(Status.submitted.name());
		dto.setCost(actualCost);
		dto.setShippingTo(orderProduct.getShippingTo());
		hashMap.put("formModel", dto);
		loadCommonFormModels(hashMap);
		return new ModelAndView("orderProduct.order", hashMap);
	}

	@RequestMapping(method = RequestMethod.POST)
	public Object save(@Valid @ModelAttribute("formModel") final OrderItemDTO formModel, final BindingResult result) {
		if (result.hasErrors()) {
			final Map<String, Object> hashMap = new HashMap<>();
			hashMap.put("formModel", formModel);
			return new ModelAndView("orderProduct.item", hashMap);
		} else {
			final IOrderItem entity = orderItemFromDtoConverter.apply(formModel);

			// validation of available product Inventory count
			final IProduct product = entity.getProduct();
			final IStore storeAvailable = storeService.getStoreByType(StoreType.available);
			IInventory availableInventory = inventoryService.getInventoryItem(storeAvailable, product);
			if (entity.getQuantity() > availableInventory.getQuantity()) {
				final Map<String, Object> hashMap = new HashMap<>();
				formModel.setQuantity(availableInventory.getQuantity());
				hashMap.put("formModel", formModel);
				return new ModelAndView("orderProduct.item", hashMap);
			} else {

				orderItemService.save(entity);
			}
			return "redirect:/orderProduct/cart";
		}

	}

	@RequestMapping(value = "/order", method = RequestMethod.POST)
	public Object confirmOrder(@Valid @ModelAttribute("formModel") final OrderProductDTO formModel,
			final BindingResult result) {
		if (result.hasErrors()) {
			final Map<String, Object> hashMap = new HashMap<>();
			hashMap.put("formModel", formModel);
			return new ModelAndView("orderProduct.order", hashMap);
		} else {
			final IOrderProduct orderProduct = orderProductFromDtoConverter.apply(formModel);

			// validation of available product Inventory count
			final IStore storeAvailable = storeService.getStoreByType(StoreType.available);
			final IStore storeReserved = storeService.getStoreByType(StoreType.reserved);
			final Integer orderProductId = orderProduct.getId();
			final OrderItemFilter orderItemFilter = new OrderItemFilter();
			orderItemFilter.setOrderProductId(orderProductId);
			orderItemFilter.setFetchProduct(true);
			orderItemFilter.setFetchOrderProduct(true);
			final List<IOrderItem> orderItems = orderItemService.find(orderItemFilter);
			List<IInventory> newInventories = new ArrayList<IInventory>();
			for (IOrderItem orderItem : orderItems) {
				IProduct product = orderItem.getProduct();
				IInventory availableInventory = inventoryService.getInventoryItem(storeAvailable, product);
				if (orderItem.getQuantity() > availableInventory.getQuantity()) {
					OrderItemDTO formModelItem = new OrderItemDTO();
					final Map<String, Object> hashMap = new HashMap<>();
					formModelItem = orderItemToDtoConverter.apply(orderItem);
					formModelItem.setQuantity(availableInventory.getQuantity());
					hashMap.put("formModel", formModelItem);
					orderProduct.setStatus(Status.productCart);
					return new ModelAndView("orderProduct.item", hashMap);
				} else {
					availableInventory.setQuantity(availableInventory.getQuantity() - orderItem.getQuantity());
					newInventories.add(availableInventory);
					IInventory reservedInventory = inventoryService.getInventoryItem(storeReserved, product);
					reservedInventory.setQuantity(reservedInventory.getQuantity() + orderItem.getQuantity());
					newInventories.add(reservedInventory);

				}
			}
			inventoryService.save(newInventories);
			orderProductService.save(orderProduct);
		}

		return "redirect:/orderProduct";
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ModelAndView viewDetails(@PathVariable(name = "id", required = true) final Integer id) {
		final IOrderItem dbModel = orderItemService.getFullInfo(id);
		final OrderItemDTO dto = orderItemToDtoConverter.apply(dbModel);
		final Map<String, Object> hashMap = new HashMap<>();
		hashMap.put("formModel", dto);
		hashMap.put("readonly", true);
		loadCommonFormModels(hashMap);
		return new ModelAndView("orderProduct.item", hashMap);
	}

	@RequestMapping(value = "/{id}/item", method = RequestMethod.GET)
	public ModelAndView edit(@PathVariable(name = "id", required = true) final Integer id) {
		final IOrderItem dbModel = orderItemService.getFullInfo(id);
		final OrderItemDTO dto = orderItemToDtoConverter.apply(dbModel);
		final Map<String, Object> hashMap = new HashMap<>();
		hashMap.put("formModel", dto);
		loadCommonFormModels(hashMap);
		return new ModelAndView("orderProduct.item", hashMap);
	}

	@RequestMapping(value = "/cart", method = RequestMethod.GET)
	public ModelAndView edit(final HttpServletRequest req,
			@RequestParam(name = "page", required = false) final Integer pageNumber,
			@RequestParam(name = "sort", required = false) final String sortColumn) {

		Integer userAccountId = AuthHelper.getLoggedUserId();
		final IUserAccount userAccount = userAccountService.get(userAccountId);
		IOrderProduct orderProduct = orderProductService.getProductCart(userAccount);
		final Integer orderProductId = orderProduct.getId();

		final GridStateDTO gridState = getListDTO(req, "cart");
		gridState.setPage(pageNumber);
		gridState.setSort(sortColumn, "product");

		final OrderItemFilter filter = new OrderItemFilter();
		filter.setOrderProductId(orderProductId);
		prepareFilter(gridState, filter);
		gridState.setTotalCount(orderItemService.getCount(filter));

		filter.setFetchProduct(true);
		filter.setFetchOrderProduct(true);

		final List<IOrderItem> entities = orderItemService.find(filter);
		List<OrderItemDTO> dtos = entities.stream().map(orderItemToDtoConverter).collect(Collectors.toList());

		final Map<String, Object> models = new HashMap<>();
		models.put("gridItems", dtos);

		return new ModelAndView("orderProduct.cart", models);
	}

	@RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
	public String delete(@PathVariable(name = "id", required = true) final Integer id) {
		orderItemService.delete(id);
		return "redirect:/orderProduct/cart";
	}

	private void loadCommonFormModels(final Map<String, Object> hashMap) {
		final List<ShippingType> shippingTypes = Arrays.asList(ShippingType.values());
		final Map<String, String> shippingTypesMap = shippingTypes.stream()
				.collect(Collectors.toMap(ShippingType::name, ShippingType::name));
		hashMap.put("shippingTypesChoices", shippingTypesMap);

	}
}
