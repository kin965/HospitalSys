package hospitalSys.controller;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import hospitalSys.bean.ClinicFindingsBean;
import hospitalSys.bean.GeneralPurposeBean;
import hospitalSys.bean.HomePageBean;
import hospitalSys.bean.IntroductoryDocumentInfoBean;
import hospitalSys.bean.OrderInfoBean;
import hospitalSys.bean.TBean;
import hospitalSys.service.HospitalSysService;

@Controller
public class HospitalSysController {
	@Autowired
	private HospitalSysService hospitalSysService;

	@RequestMapping(value = "homePage")
	public String HomePage(Model model) {

		List<HomePageBean> list = hospitalSysService.selectHomePage();
		model.addAttribute("list", list);

		return "homePage";
	}

	@GetMapping("/documentDetails/{kanjaId}")
	public String selectById(@PathVariable("kanjaId") int kanjaId, Model model) {

		OrderInfoBean oib = hospitalSysService.selectInfoByKanjaId(kanjaId);
		String url = null;
		if (oib.getDocument().equals("紹介状")) {

			url = "/showIntroduction";
			System.out.println("紹介状画面");
			System.out.println(oib.toString());

			String str = oib.getReferralHospitalInfo();
			String[] temp;
			String delimeter = " "; // 指定分割字符
			temp = str.split(delimeter); // 分割字符串
			// 普通 for 循环
			for (int i = 0; i < temp.length; i++) {
				System.out.println(temp[i]);
				if (temp[0] != null) {
					oib.setHospitalName(temp[0]);
				} else {
					oib.setHospitalName(null);
				}
				if (i >= 1 && temp[1] != null) {
					oib.setSection(temp[1]);
				} else {
					oib.setSection(null);
				}
				if (i >= 2 && temp[2] != null) {
					oib.setUserName(temp[2]);
				} else {
					oib.setUserName(null);
				}
			}
			model.addAttribute("oib", oib);

		} else if (oib.getDocument().equals("定型・診療所見")) {

			url = "/showClinicFindings";

			System.out.println(oib.toString());
			String str = oib.getReferralHospitalInfo();
			if (str != null) {
				String[] temp;
				String delimeter = " "; // 指定分割字符
				temp = str.split(delimeter); // 分割字符串
				// 普通 for 循环
				for (int i = 0; i < temp.length; i++) {
					System.out.println(temp[i]);
					if (temp[0] != null) {
						oib.setHospitalName(temp[0]);
					} else {
						oib.setHospitalName(null);
					}
					if (i >= 1 && temp[1] != null) {
						oib.setSection(temp[1]);
					} else {
						oib.setSection(null);
					}
					if (i >= 2 && temp[2] != null) {
						oib.setUserName(temp[2]);
					} else {
						oib.setUserName(null);
					}
				}
			}
			// 让页面输出的内容可以换行 页面部分的输出形式为 utext, 不然无法识别换行符
			oib.setSubjective(oib.getSubjective().replaceAll(" ", "&nbsp;").replaceAll("\r", "<br/>"));
			oib.setObjective(oib.getObjective().replaceAll(" ", "&nbsp;").replaceAll("\r", "<br/>"));
			oib.setAssessment(oib.getAssessment().replaceAll(" ", "&nbsp;").replaceAll("\r", "<br/>"));
			oib.setPlan(oib.getPlan().replaceAll(" ", "&nbsp;").replaceAll("\r", "<br/>"));

			model.addAttribute("oib", oib);

		} else if (oib.getDocument().equals("定型・汎用紹介状")) {

			url = "/showGeneralPurpose";
			String str = oib.getReferralHospitalInfo();
			if (str != null) {
				String[] temp;
				String delimeter = " "; // 指定分割字符
				temp = str.split(delimeter); // 分割字符串
				// 普通 for 循环
				for (int i = 0; i < temp.length; i++) {
					System.out.println(temp[i]);
					if (temp[0] != null) {
						oib.setHospitalName(temp[0]);
					} else {
						oib.setHospitalName(null);
					}
					if (i >= 1 && temp[1] != null) {
						oib.setSection(temp[1]);
					} else {
						oib.setSection(null);
					}
					if (i >= 2 && temp[2] != null) {
						oib.setUserName(temp[2]);
					} else {
						oib.setUserName(null);
					}
				}
			}
			model.addAttribute("oib", oib);

		}

		return url;
	}

	// @PostMapping
	@GetMapping("/selectId")
	public String selectId(Model model, @RequestParam("kanjaId") String kanjaId) {

		// 正则表达式 判断传递过来的值是否为数字
		Pattern pattern = Pattern.compile("[0-9]*");
		Matcher isNum = pattern.matcher(kanjaId);

		if (isNum.matches() && kanjaId.length() >= 1) {

			int kanjaIdS = Integer.parseInt(kanjaId);
			List<HomePageBean> list = hospitalSysService.selectIdSe(kanjaIdS);

			model.addAttribute("list", list);
		}

		return "homePage";

	}

	@PostMapping("/saveKanJa")
	public String saveKanja(Model model) {
		List<HomePageBean> demo = hospitalSysService.selectHomePage();
		model.addAttribute("demo", demo);

		return "homePage";

	}

	@GetMapping("/addTest")
	public String add(Model model) {

//model.addAttribute("kanjaInfoBean", new KanjaInfoBean());
		List<TBean> Ti = hospitalSysService.TTT();
		model.addAttribute("tBean", new TBean());
		model.addAttribute("ti", Ti);

		return "addTest";
	}

//@RequestMapping("login")
//public String save(@ModelAttribute KanjaInfoBean ki ) {
//
//System.out.println("+++++++"+ki.getName());
////hospitalSysService.saveKanja(ki);
//
//	return "addTest";
//}

	@RequestMapping("login")
	public String tB(@ModelAttribute TBean tb) {

		String url = null;
		System.out.println("+++++++++++++++" + tb.toString());
		if (tb.getKana().equals("sss")) {

			hospitalSysService.Tss(tb);
			url = "redirect:/addTest";
		} else {
			tb.setName("newName");
			hospitalSysService.Tss(tb);
			url = "homePage";
		}

		return url;
	}

//	@RequestMapping("/addInfo")
//	public String addInfo(@ModelAttribute KanjaInfoBean kanjaInfo) {
//		hospitalSysService.saveKanja(kanjaInfo);
//		System.out.println(">>>>>>>>>>>>>>>>" + kanjaInfo.toString());
//
//		return "redirect:/addKanjaInfo";
//	}

	@PostMapping("testString")
	public String testString(Model model, @RequestParam("tete") String tete) {

		return "homePage";
	}

	@GetMapping("/editDocument/{kanjaId}")
	public String editDocument(Model model, @PathVariable("kanjaId") int kanjaId) {
		String url = null;
		OrderInfoBean oib = hospitalSysService.selectInfoByKanjaId(kanjaId);
		if (oib != null) {
			if (oib.getDocument().equals("紹介状")) {

				url = "/introduction";
				IntroductoryDocumentInfoBean idi = new IntroductoryDocumentInfoBean();

				idi.setKanjaId(kanjaId);
				idi.setUketoriId(hospitalSysService.selectKanjaInfoByKanjaId(kanjaId).getUketoriId());
				idi.setName(oib.getName());
				idi.setKana(oib.getKana());
				idi.setBirthday(oib.getBirthday());
				idi.setGender(oib.getGender());
				idi.setPostalCode(oib.getPostalCode());
				idi.setAddress(oib.getAddress());
				idi.setHomeTel(oib.getHomeTel());
				idi.setWorkTel(oib.getWorkTel());
				idi.setComplaints(oib.getComplaints());
				idi.setPurpose(oib.getPurpose());
				idi.setMedicalHistory(oib.getMedicalHistory());
				idi.setDiagnosis(oib.getDiagnosis());
				idi.setFamilyHistory(oib.getFamilyHistory());
				idi.setTreatmentCourse(oib.getTreatmentCourse());
				idi.setPrescription(oib.getPrescription());
				idi.setIiView(oib.getIiView());
				idi.setHabit(oib.getHabit());
				idi.setAnnouncements(oib.getAnnouncements());
				idi.setPrescriptionStartsTime(oib.getPrescriptionStartsTime());
				idi.setPrescriptionEndTime(oib.getPrescriptionEndTime());
				idi.setPhysicalExaminationStarTime(oib.getPhysicalExaminationStarTime());
				idi.setPhysicalExaminationEndTime(oib.getPhysicalExaminationEndTime());

				// 分割原本医院医院信息
				String str = oib.getReferralHospitalInfo();
				String[] temp;
				String delimeter = " "; // 指定分割字符
				temp = str.split(delimeter); // 分割字符串
				// 普通 for 循环
				for (int i = 0; i < temp.length; i++) {
					System.out.println(temp[i]);
					if (temp[0] != null) {
						idi.setHospitalName(temp[0]);
					} else {
						idi.setHospitalName(null);
					}
					if (i >= 1 && temp[1] != null) {
						idi.setSection(temp[1]);
					} else {
						idi.setSection(null);
					}
					if (i >= 2 && temp[2] != null) {
						idi.setUserName(temp[2]);
					} else {
						idi.setUserName(null);
					}
				}

				model.addAttribute("idi", idi);

			} else if (oib.getDocument().equals("定型・汎用紹介状")) {

				url = "/generalPurpose";
				GeneralPurposeBean gpb = new GeneralPurposeBean();
				gpb.setKanjaId(kanjaId);
				gpb.setUketoriId(hospitalSysService.selectKanjaInfoByKanjaId(kanjaId).getUketoriId());
				gpb.setName(oib.getName());
				gpb.setKana(oib.getKana());
				gpb.setBirthday(oib.getBirthday());
				gpb.setGender(oib.getGender());
				gpb.setPostalCode(oib.getPostalCode());
				gpb.setAddress(oib.getAddress());
				gpb.setHomeTel(oib.getHomeTel());
				gpb.setWorkTel(oib.getWorkTel());
				gpb.setInquiryTime(oib.getInquiryTime());
				gpb.setComplaints(oib.getComplaints());
				gpb.setPurpose(oib.getPurpose());
				gpb.setMedicalHistory(oib.getMedicalHistory());
				gpb.setDiagnosis(oib.getDiagnosis());
				gpb.setFamilyHistory(oib.getFamilyHistory());
				gpb.setTreatmentCourse(oib.getTreatmentCourse());
				gpb.setPrescription(oib.getPrescription());
				gpb.setIiView(oib.getIiView());
				gpb.setHabit(oib.getHabit());
				gpb.setAnnouncements(oib.getAnnouncements());

				String str = oib.getReferralHospitalInfo();
				if (str != null) {
					String[] temp;
					String delimeter = " "; // 指定分割字符
					temp = str.split(delimeter); // 分割字符串
					// 普通 for 循环
					for (int i = 0; i < temp.length; i++) {
						System.out.println(temp[i]);
						if (temp[0] != null) {
							gpb.setHospitalName(temp[0]);
						} else {
							gpb.setHospitalName(null);
						}
						if (i >= 1 && temp[1] != null) {
							gpb.setSection(temp[1]);
						} else {
							gpb.setSection(null);
						}
						if (i >= 2 && temp[2] != null) {
							gpb.setUserName(temp[2]);
						} else {
							gpb.setUserName(null);
						}
					}
				}
				model.addAttribute("gpb", gpb);

			} else if (oib.getDocument().equals("定型・診療所見")) {
				url = "clinicFindings";

				ClinicFindingsBean cfdb = new ClinicFindingsBean();
				cfdb.setKanjaId(kanjaId);
				cfdb.setUketoriId(hospitalSysService.selectKanjaInfoByKanjaId(kanjaId).getUketoriId());
				cfdb.setName(oib.getName());
				cfdb.setKana(oib.getKana());
				cfdb.setBirthday(oib.getBirthday());
				cfdb.setGender(oib.getGender());
				cfdb.setPostalCode(oib.getPostalCode());
				cfdb.setAddress(oib.getAddress());
				cfdb.setHomeTel(oib.getHomeTel());
				cfdb.setWorkTel(oib.getWorkTel());
				cfdb.setInquiryTime(oib.getInquiryTime());
				cfdb.setSubjective(oib.getSubjective());
				cfdb.setObjective(oib.getObjective());
				cfdb.setAssessment(oib.getAssessment());
				cfdb.setPlan(oib.getPlan());

				String str = oib.getReferralHospitalInfo();
				if (str != null) {
					String[] temp;
					String delimeter = " "; // 指定分割字符
					temp = str.split(delimeter); // 分割字符串
					// 普通 for 循环
					for (int i = 0; i < temp.length; i++) {
						System.out.println(temp[i]);
						if (temp[0] != null) {
							cfdb.setHospitalName(temp[0]);
						} else {
							cfdb.setHospitalName(null);
						}
						if (i >= 1 && temp[1] != null) {
							cfdb.setSection(temp[1]);
						} else {
							cfdb.setSection(null);
						}
						if (i >= 2 && temp[2] != null) {
							cfdb.setUserName(temp[2]);
						} else {
							cfdb.setUserName(null);
						}
					}
				}

				model.addAttribute("cfdb", cfdb);

			}
		} else {
			url = "homePage";

		}

		return url;
	}

//更改状态的方法
	@GetMapping("/statusConfirmation/{documentId}")
	public String statusConfirmation(@PathVariable("documentId") int documentId) {

		hospitalSysService.statusConfirmationOfHomePage(documentId);

		return "redirect:/homePage";
	}

	// 删除文书的方法 还差点意思
	@GetMapping("/deleteDocument/{documentId}")
	public String deleteDocument(@PathVariable("documentId") int documentId) {

		hospitalSysService.deleteDocumentIdById(documentId);
		return "redirect:/homePage";
	}

}
