package hospitalSys.bean;

import java.sql.Date;
import java.sql.Timestamp;

import lombok.Data;

//定型　診療所見
@Data
public class ClinicFindingsBean {

	private Integer kanjaId;
	private Integer uketoriId;
	private  String   name;
	private  String kana;
	//@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date birthday;
	private  String gender;
	private Integer postalCode;
	private  String address;
	private  String homeTel;
	private String workTel;
	private Timestamp createTime;

	private Integer documentId;
	private String document;
    private String hospitalName;
    private String section;
    private String hospitalAddress;
    private String hospitalPostalCode;
    private String hospitalTel;
    private String userName;

    private String referralHospitalInfo;

    private Integer illnessId;
    private Date inquiryTime;
    private String subjective;
    private String objective;
    private String assessment;
    private String plan;

}
