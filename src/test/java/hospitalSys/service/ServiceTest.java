package hospitalSys.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;

import java.io.File;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import hospitalSys.bean.DocumentOrderBean;
import hospitalSys.bean.HomePageBean;
import hospitalSys.bean.IntroductoryDocumentInfoBean;

@SpringBootTest
public class ServiceTest {

	@Autowired
	HospitalSysService hospitalSysService;

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Test
	public void test() {

		HomePageBean hob = new HomePageBean();
		hob.setDocumentId(203);

		hospitalSysService.statusConfirmationOfHomePage(hob.getDocumentId());

		String sql = "select flag from document_info where document_id = 203";

		int count = jdbcTemplate.queryForObject(sql, Integer.class);

		assertEquals(2, count);
		assertTrue(count == 2);

	}

	@Test
	void checkKanjaId() {

//
//        String count ="[DocumentOrderBean(kanjaId=23, name=二子, kana=イ, birthday=2022-02-14, gender=女性, postalCode=1700015, address=大塚1, homeTel=08077778866, workTel=9011112223, illnessId=42, complaints=頭が痛い, purpose=転院, medicalHistory=特になし, familyHistory=特になし, diagnosis=頭が壁にぶつかった, treatmentCourse=絆創膏を貼る, prescription=絆創膏, iiView=背が高い, habit=壁が好き, announcements=午前中に連絡しない, iiCreateTime=2022-03-18 20:03:30.265, documentId=234, document=紹介状, referralHospitalInfo=長汐病院 外科 ご苦労様, formerHospitalInfo=null, flag=1, diCreateTime=2022-03-18 20:03:30.265)]\r\n"
//        		+ "";

		List<DocumentOrderBean> hSS = hospitalSysService.selectByKanjaId(23);
		for (DocumentOrderBean tmp : hSS) {
			assertEquals(tmp.getKanjaId(), 23);
			assertEquals(tmp.getName(), "二子");
			assertEquals(tmp.getAddress(), "大塚1");

		}
//System.out.println(hSS);

		// System.err.println(hospitalSysService.selectByKanjaId(23).toString());

	}

	@Test
	void insertDocumentTest() {

		IntroductoryDocumentInfoBean idi = new IntroductoryDocumentInfoBean();

		// hospitalSysService.saveIntroduction(idi);

	}

}
