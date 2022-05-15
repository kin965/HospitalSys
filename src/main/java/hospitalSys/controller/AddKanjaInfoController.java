package hospitalSys.controller;

import java.sql.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import hospitalSys.bean.ClinicFindingsBean;
import hospitalSys.bean.GeneralPurposeBean;
import hospitalSys.bean.IntroductoryDocumentInfoBean;
import hospitalSys.bean.KanjaInfoBean;
import hospitalSys.service.HospitalSysService;

@Controller
public class AddKanjaInfoController {

	@Autowired
	private HospitalSysService hospitalSysService;

	@GetMapping("/addKanjaInfo")
	public String addKanja(Model model) {

//		//現在の日付データを生成
//		LocalDateTime localDateTime = LocalDateTime.now();
//
//		//書式を指定
//		DateTimeFormatter datetimeformatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
//
//		//指定の書式に日付データを渡す
//		String datetimeformated = datetimeformatter.format(localDateTime);
//
//		model.addAttribute("datetimeformated", datetimeformated);

		model.addAttribute("kanjaInfo", new KanjaInfoBean());

		return "addKanjaInfo";
	}

	// 选择不同文书类型 带着用户信息跳转到新页面的方法
	@PostMapping("/try")
	public String DocumentInformationCreation(Model model, @ModelAttribute KanjaInfoBean kanjaInfo,
			@RequestParam("DocumentType") String DocumentType) {

//			if (!kanjaInfo.getBirthday().matches("^[0-9]{4}-[0-9]{2}-[0-9]{2}$")) {
//				// ...
//				return "redirect:/error";
//			}

		// 设定一个变动的url 根据返回的文书类型 跳转到不同页面
		String url = null;
		IntroductoryDocumentInfoBean idi = new IntroductoryDocumentInfoBean();
		if (kanjaInfo != null && DocumentType.equals("紹介状")) {
			System.out.println("<<++++++++++++++>>" + kanjaInfo.toString());
			// 紹介状ｕｒｌ
			url = "/introduction";

			// model.addAttribute("kanjainfo", kanjaInfo);
			// 新建一个bean对象 将取到的值放到新bean中，然后插入新页面
			idi.setKanjaId(kanjaInfo.getKanjaId());
			idi.setUketoriId(kanjaInfo.getUketoriId());
			idi.setName(kanjaInfo.getName());
			idi.setKana(kanjaInfo.getKana());
			// string -> javax.sql.Date

			idi.setBirthday(kanjaInfo.getBirthday());

//			    Date date = sdFormat.parse(strDate);
//			    idi.setBirthday(kanjaInfo.getBirthday());

			idi.setGender(kanjaInfo.getGender());
			idi.setPostalCode(kanjaInfo.getPostalCode());
			idi.setAddress(kanjaInfo.getAddress());
			idi.setHomeTel(kanjaInfo.getHomeTel());
			idi.setWorkTel(kanjaInfo.getWorkTel());

			// idi.setHospitalName("xxxxx医院");
//			Date ti = new Date(System.currentTimeMillis());
//			idi.setPrescriptionStartsTime(ti);
			System.out.println("介绍状页面注入的数据" + idi.toString());
			model.addAttribute("idi", idi);

		} else if (kanjaInfo != null && DocumentType.equals("【定型】汎用紹介状")) {

			// 定型(汎用紹介状)url
			url = "/generalPurpose";

			GeneralPurposeBean gpb = new GeneralPurposeBean();
			gpb.setKanjaId(kanjaInfo.getKanjaId());
			gpb.setUketoriId(kanjaInfo.getUketoriId());
			gpb.setName(kanjaInfo.getName());
			gpb.setKana(kanjaInfo.getKana());
			gpb.setBirthday(kanjaInfo.getBirthday());
			gpb.setGender(kanjaInfo.getGender());
			gpb.setPostalCode(kanjaInfo.getPostalCode());
			gpb.setAddress(kanjaInfo.getAddress());
			gpb.setHomeTel(kanjaInfo.getHomeTel());
			gpb.setWorkTel(kanjaInfo.getWorkTel());

			Date time = new Date(System.currentTimeMillis());
			gpb.setInquiryTime(time);

			model.addAttribute("gpb", gpb);
		} else if (kanjaInfo != null && DocumentType.equals("【定型】診療所見")) {

			ClinicFindingsBean cfdb = new ClinicFindingsBean();

			// 定型(診療所見)url
			url = "/clinicFindings";

			cfdb.setKanjaId(kanjaInfo.getKanjaId());
			cfdb.setUketoriId(kanjaInfo.getUketoriId());
			cfdb.setName(kanjaInfo.getName());
			cfdb.setKana(kanjaInfo.getKana());
			cfdb.setBirthday(kanjaInfo.getBirthday());
			cfdb.setGender(kanjaInfo.getGender());
			cfdb.setPostalCode(kanjaInfo.getPostalCode());
			cfdb.setAddress(kanjaInfo.getAddress());
			cfdb.setHomeTel(kanjaInfo.getHomeTel());
			cfdb.setWorkTel(kanjaInfo.getWorkTel());

			Date time = new Date(System.currentTimeMillis());
			cfdb.setInquiryTime(time);

			model.addAttribute("cfdb", cfdb);

		}

		return url;
	}

	@GetMapping("/intoForm")
	public String intoForm(Model model, @RequestParam("kanjaIdx") String kaId) {

		Pattern pattern = Pattern.compile("[0-9]*");
		Matcher isNum = pattern.matcher(kaId);

		if (isNum.matches() && kaId.length() >= 1) {
			int kanjaId = Integer.parseInt(kaId);
			KanjaInfoBean kanjaInfo = hospitalSysService.selectKanjaInfoByKanjaId(kanjaId);

			if (kanjaInfo != null) {
				model.addAttribute("kanjaInfo", kanjaInfo);
				// System.out.println("<<++++++++++++++>>" + kanjaInfo.toString());
				String ssss = kaId;
				String Presentation = "これは患者IDが" + kaId + "番の患者さんの情報: ↓↓↓";
				model.addAttribute("Presentation", Presentation);
				model.addAttribute("ssss", ssss);
			} else {
				kanjaInfo = new KanjaInfoBean();
				// kanjaInfo.setAddress("no data ******+++++++++++");
				model.addAttribute("kanjaInfo", kanjaInfo);
				String Presentation = "入力された患者IDに該当がありません,新規患者情報入力してください　↓↓↓　";
				model.addAttribute("Presentation", Presentation);
			}
		} else {

			KanjaInfoBean kanjaInfo = new KanjaInfoBean();
			// kanjaInfo.setAddress("no data ******+++++++++++");
			model.addAttribute("kanjaInfo", kanjaInfo);
		}
		return "addKanjaInfo";
	}

}
