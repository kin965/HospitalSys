package hospitalSys.controller;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import hospitalSys.bean.IntroductoryDocumentInfoBean;
import hospitalSys.bean.KanjaInfoBean;

@SpringBootTest
public class ControllerTest {


	@Test
	public String DocumentInformationCreation(Model model, @ModelAttribute KanjaInfoBean kanjaInfo,
			@RequestParam("DocumentType") String DocumentType) {

//		if (!kanjaInfo.getBirthday().matches("^[0-9]{4}-[0-9]{2}-[0-9]{2}$")) {
//			// ...
//			return "redirect:/error";
//		}

		// 设定一个变动的url 根据返回的文书类型 跳转到不同页面
		String url = null;
		IntroductoryDocumentInfoBean idi = new IntroductoryDocumentInfoBean();
		if (kanjaInfo != null && DocumentType.equals("紹介状")) {

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


//		    Date date = sdFormat.parse(strDate);
//		    idi.setBirthday(kanjaInfo.getBirthday());

			idi.setGender(kanjaInfo.getGender());
			idi.setPostalCode(kanjaInfo.getPostalCode());
			idi.setAddress(kanjaInfo.getAddress());
			idi.setHomeTel(kanjaInfo.getHomeTel());
			idi.setWorkTel(kanjaInfo.getWorkTel());

			System.out.println(">>>>>>>>>><<<<<<<<"+idi.getGender());



			model.addAttribute("idi", idi);

		} else if (kanjaInfo != null && DocumentType.equals("【定型】汎用紹介状")) {

			// 定型url
			url = "/generalPurpose";

			model.addAttribute("kanjainfo", kanjaInfo);
		} else if (kanjaInfo != null && DocumentType.equals("【定型】診療所見")) {

			// 定型url
			url = "/clinicFindings";
			model.addAttribute("kanjainfo", kanjaInfo);
		}

		return url;
	}
}
