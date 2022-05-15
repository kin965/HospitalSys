package hospitalSys.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import hospitalSys.bean.ClinicFindingsBean;
import hospitalSys.bean.IntroductoryDocumentInfoBean;
import hospitalSys.bean.KanjaInfoBean;
import hospitalSys.service.HospitalSysService;

@Controller
public class ClinicFindingsController {

	@Autowired
	private HospitalSysService hospitalSysService;

	// 保存定型诊疗所见
	@PostMapping("/saveClinicFindings")
	public String saveClinicFindings(Model model, @ModelAttribute ClinicFindingsBean cfdb) {
		KanjaInfoBean kanjaInfo = hospitalSysService.selectKanjaInfoByKanjaId(cfdb.getKanjaId());
		if (kanjaInfo == null) {

			IntroductoryDocumentInfoBean idi = new IntroductoryDocumentInfoBean();
			idi.setKanjaId(cfdb.getKanjaId());
			if (cfdb.getUketoriId() != null) {
				idi.setUketoriId(cfdb.getUketoriId());
			} else {
				idi.setUketoriId(cfdb.getKanjaId());
			}
			idi.setName(cfdb.getName());
			idi.setKana(cfdb.getKana());
			idi.setBirthday(cfdb.getBirthday());
			idi.setGender(cfdb.getGender());
			idi.setPostalCode(cfdb.getPostalCode());
			idi.setAddress(cfdb.getAddress());
			idi.setHomeTel(cfdb.getHomeTel());
			idi.setWorkTel(cfdb.getWorkTel());
			hospitalSysService.saveNewKanja(idi);

			cfdb.setIllnessId(hospitalSysService.selectMaxIdByI() + 1);
			cfdb.setDocumentId(hospitalSysService.selectMaxIdbyD() + 1);
			cfdb.setUketoriId(cfdb.getKanjaId());
			cfdb.setDocument("定型・診療所見");

			hospitalSysService.saveClinicFindingsBean(cfdb);

		} else {

			cfdb.setIllnessId(hospitalSysService.selectMaxIdByI() + 1);
			cfdb.setDocumentId(hospitalSysService.selectMaxIdbyD() + 1);
			cfdb.setDocument("定型・診療所見");
			cfdb.setUketoriId(cfdb.getKanjaId());
			hospitalSysService.saveClinicFindingsBean(cfdb);

		}

		return "redirect:/homePage";
	}

}
