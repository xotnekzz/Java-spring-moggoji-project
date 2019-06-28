package dev.mvc.director;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import kr.or.kobis.kobisopenapi.consumer.rest.KobisOpenAPIRestService;
import kr.or.kobis.kobisopenapi.consumer.rest.exception.OpenAPIFault;

@Controller
public class DirectorCont {

    @Autowired
    @Qualifier("dev.mvc.director.DirectorProc") // �Ҵ�Ǵ� Class ��ü�� �̸�.
    private DirectorProcInter directorProc;
    
    
    public DirectorCont() {
        //System.out.println("DirectorCont ����Ǿ���.");
    }
    
    
    // �����Ϳ��� ���� DB�� �����ϴ� ��Ʈ�ѷ� �κ�. 
/*  @RequestMapping(value="/director/create.do", method=RequestMethod.GET)
    public ModelAndView create() throws OpenAPIFault, Exception {
    ModelAndView mav = new ModelAndView();

    String curPage = "5"; // ��� ° ������ ������ ���� �� ������?
    String itemPerPage = "34000"; // 17������ �����͸� ��� ���� ������?
    String peopleNm = "";
    String filmoNames = "";

    String key = "e9d0301005f12bb578caf805757ad88f";

    KobisOpenAPIRestService service = new KobisOpenAPIRestService(key);

    String peopleList = "";

    peopleList = service.getPeopleList(true, curPage, itemPerPage, peopleNm, filmoNames);
    peopleList = "{ " + peopleList.substring(37, peopleList.length() - 22) + " } ";
 
    JSONParser parser = new JSONParser();
    JSONObject jsonobj = null;
    jsonobj = (JSONObject) parser.parse(peopleList);

    JSONArray jsonarray = (JSONArray) jsonobj.get("peopleList");

    DirectorVO directorVO;
    
    for (int i = 0; i < jsonarray.size(); i++) {
      JSONObject peopleObj = (JSONObject) jsonarray.get(i);
      if (peopleObj.get("repRoleNm").equals("����")) {

        directorVO = new DirectorVO();

        directorVO.setDirectorNm(peopleObj.get("peopleNm").toString());

        if (peopleObj.get("peopleNmEn") != null) {
          directorVO.setDirectorEnNm(peopleObj.get("peopleNmEn").toString());
        } else {
          directorVO.setDirectorEnNm("");
        }
        directorVO.setDcode(peopleObj.get("peopleCd").toString());

        directorProc.create(directorVO);
      } 
    }
    System.out.println("jsonȣ��5");
    mav.setViewName("");
    return mav; 
  }*/
}
