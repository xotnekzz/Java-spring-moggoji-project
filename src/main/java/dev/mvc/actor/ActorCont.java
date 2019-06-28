package dev.mvc.actor;

import kr.or.kobis.kobisopenapi.consumer.rest.KobisOpenAPIRestService;
import kr.or.kobis.kobisopenapi.consumer.rest.exception.OpenAPIFault;
  
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ActorCont {
 @Autowired
 @Qualifier("dev.mvc.actor.ActorProc")
 private ActorProcInter actorProc;   
   
 
 public ActorCont() {
  //System.out.println("--> ActorCont Created.");
 }
 
/**
 * ��ȭ ���������� ����
 */
 
 /*@RequestMapping(value="/actor/create.do", method=RequestMethod.GET)
 public ModelAndView create() throws OpenAPIFault, Exception {
   ModelAndView mav = new ModelAndView();
   mav.setViewName("/actor"); 
   
    //��ȭ ���������� �ڵ� ���� 
   String curPage = "5"; // ������ �������� ���°��
   String itemPerPage = "34000"; // 17������ ��� ����������
   String peopleNm = "";  
   String filmoNames = "";   
   String peopleList = "";  
      
   String key = "e9d0301005f12bb578caf805757ad88f";
   
   KobisOpenAPIRestService service = new KobisOpenAPIRestService(key);
   
   peopleList = service.getPeopleList(true, curPage, itemPerPage, peopleNm, filmoNames); // ��ȭ�� ���� ��û

   peopleList = "{ " + peopleList.substring(37, peopleList.length()-22) + " } ";
//   System.out.println(peopleList); 
      
   JSONParser parser = new JSONParser();
   JSONObject jsonobj = (JSONObject)parser.parse(peopleList);
   
   JSONArray jsonarray = (JSONArray)jsonobj.get("peopleList"); 
     
   for (int i=0; i<jsonarray.size() ; i++) { 
    JSONObject peopleObj = (JSONObject)jsonarray.get(i); 
     
    if(peopleObj.get("repRoleNm").toString().equals("���")) {
      ActorVO actorVO = new ActorVO();
      actorVO.setActorNm(peopleObj.get("peopleNm").toString());
      
      if(peopleObj.get("peopleNmEn") !=null ){ 
        actorVO.setActorEnNm(peopleObj.get("peopleNmEn").toString());
      } else { 
        actorVO.setActorEnNm("");   
      }
       
      actorVO.setAcode(peopleObj.get("peopleCd").toString());
      actorProc.create(actorVO);   
    } 
   }  
   System.out.println("json ȣ��5");
   
    //��ȭ ���������� �ڵ� ���� �� 
   
   return mav;
 }
 */
}
