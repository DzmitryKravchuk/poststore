package com.itacademy.jd2.dk.poststore.web.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.itacademy.jd2.dk.poststore.dao.api.entity.enums.ImageFormat;
import com.itacademy.jd2.dk.poststore.dao.api.entity.table.IPaperDetails;
import com.itacademy.jd2.dk.poststore.dao.api.entity.table.IPolygraphy;
import com.itacademy.jd2.dk.poststore.dao.api.filter.PolygraphyFilter;
import com.itacademy.jd2.dk.poststore.service.IPaperDetailsService;
import com.itacademy.jd2.dk.poststore.service.IPolygraphyService;
import com.itacademy.jd2.dk.poststore.web.converter.PolygraphyFromDTOConverter;
import com.itacademy.jd2.dk.poststore.web.converter.PolygraphyToDTOConverter;
import com.itacademy.jd2.dk.poststore.web.dto.PolygraphyDTO;
import com.itacademy.jd2.dk.poststore.web.dto.grid.GridStateDTO;
import com.itacademy.jd2.dk.poststore.web.security.AuthHelper;

@Controller
@RequestMapping(value = "/polygraphy")
public class PolygraphyController extends AbstractController {

	public static final String FILE_FOLDER = "d:\\uploadedFiles\\";

	@PostConstruct
	private void init() {

		File rootFolder = new File(FILE_FOLDER);
		if (!rootFolder.exists()) {
			rootFolder.mkdirs();
		}
	}

	@Autowired
	private IPolygraphyService polygraphyService;

	@Autowired
	private IPaperDetailsService paperDetailsService;

	@Autowired
	private PolygraphyFromDTOConverter fromDtoConverter;

	@Autowired
	private PolygraphyToDTOConverter toDtoConverter;

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView index(final HttpServletRequest req,
			@RequestParam(name = "page", required = false) final Integer pageNumber,
			@RequestParam(name = "sort", required = false) final String sortColumn) {

		Integer userAccountId = AuthHelper.getLoggedUserId();

		final GridStateDTO gridState = getListDTO(req);
		gridState.setPage(pageNumber);
		gridState.setSort(sortColumn, "updated");

		final PolygraphyFilter filter = new PolygraphyFilter();
		filter.setUserAccountId(userAccountId);

		prepareFilter(gridState, filter);
		gridState.setTotalCount(polygraphyService.getCount(filter));

		filter.setFetchPaperDetails(true);
		filter.setFetchUserAccount(true);

		final List<IPolygraphy> entities = polygraphyService.find(filter); // searching for all entities

		List<PolygraphyDTO> dtos = entities.stream().map(toDtoConverter).collect(Collectors.toList());

		final Map<String, Object> models = new HashMap<>();
		models.put("gridItems", dtos);
		return new ModelAndView("polygraphy.list", models);
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)

	public ModelAndView showForm() {
		Integer userAccountId = AuthHelper.getLoggedUserId();

		final Map<String, Object> hashMap = new HashMap<>();
		final PolygraphyDTO dto = new PolygraphyDTO();
		dto.setCopyCount(1); // set default minimum value
		dto.setColoured(false);
		dto.setDuplexPrinting(false);
		dto.setUserAccountId(userAccountId);
		hashMap.put("formModel", dto);
		loadCommonFormModels(hashMap);
		return new ModelAndView("polygraphy.edit", hashMap);
	}

	@RequestMapping(method = RequestMethod.POST)
	public Object save(@Valid @ModelAttribute("formModel") final PolygraphyDTO formModel, final BindingResult result,
			final MultipartFile file, final RedirectAttributes redirectAttributes)
			throws IOException, GeneralSecurityException {

		if (result.hasErrors()) {
			final Map<String, Object> hashMap = new HashMap<>();
			hashMap.put("formModel", formModel);

			loadCommonFormModels(hashMap);
			return new ModelAndView("polygraphy.edit", hashMap);
		} else {
			final IPolygraphy entity = fromDtoConverter.apply(formModel);

			polygraphyService.save(entity);

			String uuid = entity.getId().toString();
			InputStream inputStream = file.getInputStream();
			Files.copy(inputStream, new File(FILE_FOLDER + uuid).toPath(), StandardCopyOption.REPLACE_EXISTING);

			return "redirect:/polygraphy";
		}
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ModelAndView viewDetails(@PathVariable(name = "id", required = true) final Integer id) {

		final IPolygraphy dbModel = polygraphyService.getFullInfo(id);
		final PolygraphyDTO dto = toDtoConverter.apply(dbModel);
		final Map<String, Object> hashMap = new HashMap<>();
		hashMap.put("formModel", dto);
		hashMap.put("readonly", true);
		loadCommonFormModels(hashMap);
		return new ModelAndView("polygraphy.edit", hashMap);
	}

	@RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
	public ModelAndView edit(@PathVariable(name = "id", required = true) final Integer id) {
		final PolygraphyDTO dto = toDtoConverter.apply(polygraphyService.getFullInfo(id));

		final Map<String, Object> hashMap = new HashMap<>();
		hashMap.put("formModel", dto);
		loadCommonFormModels(hashMap);
		return new ModelAndView("polygraphy.edit", hashMap);
	}

	@RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
	public String delete(@PathVariable(name = "id", required = true) final Integer id) {
		polygraphyService.delete(id);
		return "redirect:/polygraphy";
	}

	private void loadCommonFormModels(final Map<String, Object> hashMap) {

		final List<IPaperDetails> paperDetailses = paperDetailsService.getAll();
		final Map<Integer, String> paperDetailsMap = paperDetailses.stream()
				.collect(Collectors.toMap(IPaperDetails::getId, IPaperDetails::getName));
		hashMap.put("paperDetailsChoices", paperDetailsMap);

		final List<ImageFormat> imageFormatsList = Arrays.asList(ImageFormat.values());
		final Map<String, String> imageFormatsMap = imageFormatsList.stream()
				.collect(Collectors.toMap(ImageFormat::name, ImageFormat::name));
		hashMap.put("imageFormatsChoices", imageFormatsMap);

	}

}
