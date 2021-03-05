package com.itacademy.jd2.dk.poststore.web.controller;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.itacademy.jd2.dk.poststore.dao.api.entity.enums.Status;
import com.itacademy.jd2.dk.poststore.dao.api.entity.enums.StoreType;
import com.itacademy.jd2.dk.poststore.dao.api.entity.enums.UserRole;
import com.itacademy.jd2.dk.poststore.dao.api.entity.table.IInventory;
import com.itacademy.jd2.dk.poststore.dao.api.entity.table.IOrderItem;
import com.itacademy.jd2.dk.poststore.dao.api.entity.table.IOrderProduct;
import com.itacademy.jd2.dk.poststore.dao.api.entity.table.IProduct;
import com.itacademy.jd2.dk.poststore.dao.api.entity.table.IStore;
import com.itacademy.jd2.dk.poststore.dao.api.entity.table.IUserAccount;
import com.itacademy.jd2.dk.poststore.dao.api.filter.OrderItemFilter;
import com.itacademy.jd2.dk.poststore.dao.api.filter.OrderProductFilter;
import com.itacademy.jd2.dk.poststore.dao.api.filter.ProductFilter;
import com.itacademy.jd2.dk.poststore.service.IInventoryService;
import com.itacademy.jd2.dk.poststore.service.IOrderItemService;
import com.itacademy.jd2.dk.poststore.service.IOrderProductService;
import com.itacademy.jd2.dk.poststore.service.IProductService;
import com.itacademy.jd2.dk.poststore.service.IStoreService;
import com.itacademy.jd2.dk.poststore.service.IUserAccountService;
import com.itacademy.jd2.dk.poststore.web.converter.OrderItemToDTOConverter;
import com.itacademy.jd2.dk.poststore.web.converter.OrderProductToDTOConverter;
import com.itacademy.jd2.dk.poststore.web.dto.OrderItemDTO;
import com.itacademy.jd2.dk.poststore.web.dto.OrderProductDTO;
import com.itacademy.jd2.dk.poststore.web.dto.ProductCountDto;
import com.itacademy.jd2.dk.poststore.web.dto.grid.GridStateDTO;
import com.itacademy.jd2.dk.poststore.web.security.AuthHelper;

@Controller
@RequestMapping(value = "/orderHistory")
public class OrderHistoryController extends AbstractController {

	@Autowired
	private IUserAccountService userAccountService;
	@Autowired
	private IOrderItemService orderItemService;
	@Autowired
	private OrderItemToDTOConverter orderItemToDtoConverter;

	@Autowired
	private IProductService productService;

	@Autowired
	private IStoreService storeService;

	@Autowired
	private IInventoryService inventoryService;

	@Autowired
	private OrderProductToDTOConverter orderProductToDtoConverter;

	@Autowired
	private IOrderProductService orderProductService;

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView index(final HttpServletRequest req,
			@RequestParam(name = "page", required = false) final Integer pageNumber,
			@RequestParam(name = "sort", required = false) final String sortColumn) {

		Integer userAccountId = AuthHelper.getLoggedUserId();
		IUserAccount loggedUser = userAccountService.get(userAccountId);
		final Map<String, Object> models = new HashMap<>();

		if (loggedUser.getUserRole() == UserRole.customer) {
			final GridStateDTO gridState = getListDTO(req);
			gridState.setPage(pageNumber);
			gridState.setSort(sortColumn, "updated");

			final OrderProductFilter filter = new OrderProductFilter();
			filter.setUserAccountId(userAccountId);
			prepareFilter(gridState, filter);
			gridState.setTotalCount(orderProductService.getCount(filter));
			filter.setFetchUserAccount(true);
			final List<IOrderProduct> entities = orderProductService.find(filter);
			List<OrderProductDTO> dtos = entities.stream().map(orderProductToDtoConverter).collect(Collectors.toList());

			models.put("gridItems", dtos);
		} else if (loggedUser.getUserRole() == UserRole.admin || loggedUser.getUserRole() == UserRole.manager) {

			final GridStateDTO gridState = getListDTO(req);
			gridState.setPage(pageNumber);
			gridState.setSort(sortColumn, "updated");

			final OrderProductFilter filter = new OrderProductFilter();
			filter.setStatus(Status.submitted);
			prepareFilter(gridState, filter);
			gridState.setTotalCount(orderProductService.getCount(filter));
			filter.setFetchUserAccount(true);
			final List<IOrderProduct> entities = orderProductService.find(filter);
			List<OrderProductDTO> dtos = entities.stream().map(orderProductToDtoConverter).collect(Collectors.toList());

			models.put("gridItems", dtos);
		}

		List<ProductCountDto> mostPopularProducts = getMostPopularProducts();
		StringBuilder sb = new StringBuilder("");
		sb.append("[");
		sb.append("['col1','col2'],");
		for (ProductCountDto productCountDto : mostPopularProducts) {
			sb.append("[");
			sb.append("'" + productCountDto.getProductName() + "',");
			sb.append(productCountDto.getCount());
			sb.append("],");
		}
		sb.append("]");

		models.put("jsData", sb.toString());

		return new ModelAndView("orderHistory.list", models);

	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ModelAndView viewDetails(final HttpServletRequest req,
			@RequestParam(name = "page", required = false) final Integer pageNumber,
			@RequestParam(name = "sort", required = false) final String sortColumn,
			@PathVariable(name = "id", required = true) final Integer id) {

		final Integer orderProductId = id;

		final GridStateDTO gridState = getListDTO(req, "list");
		gridState.setPage(pageNumber);
		gridState.setSort(sortColumn, "id");

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
		models.put("orderProductId", orderProductId);
		return new ModelAndView("orderHistory.cart", models);
	}

	@RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
	public Object save(@PathVariable(name = "id", required = true) final Integer id) {
		final IOrderProduct orderProduct = orderProductService.get(id);

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
			IInventory reservedInventory = inventoryService.getInventoryItem(storeReserved, product);
			reservedInventory.setQuantity(reservedInventory.getQuantity() - orderItem.getQuantity());
			newInventories.add(reservedInventory);

		}
		inventoryService.save(newInventories);
		orderProduct.setStatus(Status.executed);
		orderProductService.save(orderProduct);
		return "redirect:/orderHistory";
	}

	private List<ProductCountDto> getMostPopularProducts() {
		final Map<Integer, Integer> unratedProducts = new HashMap<>();

		final ProductFilter productFilter = new ProductFilter();
		final List<IProduct> products = productService.find(productFilter);
		for (IProduct product : products) {
			unratedProducts.put(product.getId(), 0);
		}

		List<IOrderProduct> orderProducts = orderProductService.getPreviousMonthOrders();
		final List<IOrderItem> allOrderItems = new ArrayList<IOrderItem>();

		for (IOrderProduct orderProduct : orderProducts) {
			final OrderItemFilter orderItemFilter = new OrderItemFilter();
			orderItemFilter.setOrderProductId(orderProduct.getId());
			orderItemFilter.setFetchProduct(true);
			orderItemFilter.setFetchOrderProduct(true);
			final List<IOrderItem> orderItems = orderItemService.find(orderItemFilter);
			allOrderItems.addAll(orderItems);
		}

		for (IOrderItem orderItem : allOrderItems) {

			IProduct product = orderItem.getProduct();
			Integer integer = unratedProducts.get(product.getId());
			int i = integer + orderItem.getQuantity();
			unratedProducts.put(product.getId(), i); // LOOK UP
		}

		ValueComparator bvc = new ValueComparator(unratedProducts);
		TreeMap<Integer, Integer> ratedProducts = new TreeMap<Integer, Integer>(bvc);
		ratedProducts.putAll(unratedProducts);

		Set<Entry<Integer, Integer>> entrySet = ratedProducts.entrySet();

		List<ProductCountDto> result = new ArrayList<>();
		for (Entry<Integer, Integer> entry : entrySet) {
			ProductCountDto productCountDto = new ProductCountDto();
			IProduct product = productService.get(entry.getKey());
			productCountDto.setProductName(product.getName());
			productCountDto.setCount(entry.getValue());
			result.add(productCountDto);
		}

		List<ProductCountDto> result5 = new ArrayList<>();
		result5.add(result.get(0));
		result5.add(result.get(1));
		result5.add(result.get(2));
		result5.add(result.get(3));
		result5.add(result.get(4));
		return result5;
	}

	class ValueComparator implements Comparator<Integer> {
		Map<Integer, Integer> base;

		public ValueComparator(Map<Integer, Integer> unratedProducts) {
			this.base = unratedProducts;
		}

		// Note: this comparator imposes orderings that are inconsistent with
		// equals.
		public int compare(Integer a, Integer b) {
			if (base.get(a) >= base.get(b)) {
				return -1;
			} else {
				return 1;
			} // returning 0 would merge keys
		}
	}
}
