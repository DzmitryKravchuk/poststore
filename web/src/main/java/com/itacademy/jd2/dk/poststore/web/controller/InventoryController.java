package com.itacademy.jd2.dk.poststore.web.controller;

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

import com.itacademy.jd2.dk.poststore.dao.api.entity.enums.StoreType;
import com.itacademy.jd2.dk.poststore.dao.api.entity.table.IInventory;
import com.itacademy.jd2.dk.poststore.dao.api.entity.table.IProduct;
import com.itacademy.jd2.dk.poststore.dao.api.entity.table.IStore;
import com.itacademy.jd2.dk.poststore.dao.api.filter.InventoryFilter;
import com.itacademy.jd2.dk.poststore.service.IInventoryService;
import com.itacademy.jd2.dk.poststore.service.IProductService;
import com.itacademy.jd2.dk.poststore.service.IStoreService;
import com.itacademy.jd2.dk.poststore.web.converter.InventoryToDTOConverter;
import com.itacademy.jd2.dk.poststore.web.dto.InventoryDTO;
import com.itacademy.jd2.dk.poststore.web.dto.grid.GridStateDTO;

@Controller
@RequestMapping(value = "/inventory")
public class InventoryController extends AbstractController {
	@Autowired
	private IInventoryService inventoryService;
	@Autowired
	private IProductService productService;
	@Autowired
	private IStoreService storeService;

	@Autowired
	private InventoryToDTOConverter toDtoConverter;

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView index(final HttpServletRequest req,
			@RequestParam(name = "page", required = false) final Integer pageNumber,
			@RequestParam(name = "sort", required = false) final String sortColumn) {

		final GridStateDTO gridState = getListDTO(req);
		gridState.setPage(pageNumber);
		gridState.setSort(sortColumn, "product");

		final InventoryFilter filter = new InventoryFilter();
		prepareFilter(gridState, filter);
		gridState.setTotalCount(inventoryService.getCount(filter));

		filter.setFetchProduct(true);
		filter.setFetchStore(true);
		final List<IInventory> entities = inventoryService.find(filter);
		List<InventoryDTO> dtos = entities.stream().map(toDtoConverter).collect(Collectors.toList());

		final Map<String, Object> models = new HashMap<>();
		models.put("gridItems", dtos);
		return new ModelAndView("inventory.list", models);
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView showForm() {
		final Map<String, Object> hashMap = new HashMap<>();
		InventoryDTO dto = new InventoryDTO();
		IStore store = storeService.getStoreByType(StoreType.available);
		dto.setStoreId(store.getId());
		hashMap.put("formModel", dto);

		loadCommonFormModels(hashMap);
		return new ModelAndView("inventory.edit", hashMap);
	}

	@RequestMapping(method = RequestMethod.POST)
	public Object save(@Valid @ModelAttribute("formModel") final InventoryDTO formModel, final BindingResult result) {
		if (result.hasErrors()) {
			final Map<String, Object> hashMap = new HashMap<>();
			hashMap.put("formModel", formModel);
			loadCommonFormModels(hashMap);
			return new ModelAndView("inventory.edit", hashMap);
		} else {
			IStore store = storeService.get(formModel.getStoreId());
			IProduct product = productService.get(formModel.getProductId());
			final IInventory entity = inventoryService.getInventoryItem(store, product);
			final Integer newQuantity = entity.getQuantity() + formModel.getQuantity();
			entity.setQuantity(newQuantity);

			inventoryService.save(entity);
			return "redirect:/inventory";
		}
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ModelAndView viewDetails(@PathVariable(name = "id", required = true) final Integer id) {
		final IInventory dbModel = inventoryService.getFullInfo(id);
		final InventoryDTO dto = toDtoConverter.apply(dbModel);
		final Map<String, Object> hashMap = new HashMap<>();
		hashMap.put("formModel", dto);
		hashMap.put("readonly", true);
		loadCommonFormModels(hashMap);
		return new ModelAndView("inventory.edit", hashMap);
	}

	@RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
	public ModelAndView edit(@PathVariable(name = "id", required = true) final Integer id) {
		final InventoryDTO dto = toDtoConverter.apply(inventoryService.getFullInfo(id));

		final Map<String, Object> hashMap = new HashMap<>();
		hashMap.put("formModel", dto);
		loadCommonFormModels(hashMap);
		return new ModelAndView("inventory.edit", hashMap);
	}

	@RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
	public String delete(@PathVariable(name = "id", required = true) final Integer id) {
		inventoryService.delete(id);
		return "redirect:/inventory";
	}

	private void loadCommonFormModels(final Map<String, Object> hashMap) {

		final List<IProduct> products = productService.getAll();
		final Map<Integer, String> productsMap = products.stream()
				.collect(Collectors.toMap(IProduct::getId, IProduct::getName));
		hashMap.put("productsChoices", productsMap);

	}

}
