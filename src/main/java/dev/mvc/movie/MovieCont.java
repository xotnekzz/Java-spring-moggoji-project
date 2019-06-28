package dev.mvc.movie;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONObject;
/*import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;*/
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import dev.mvc.tool.Tool;
import dev.mvc.tool.Upload;
import kr.or.kobis.kobisopenapi.consumer.rest.KobisOpenAPIRestService;
import kr.or.kobis.kobisopenapi.consumer.rest.exception.OpenAPIFault;

@Controller
public class MovieCont {
 
  @Autowired 
  @Qualifier("dev.mvc.movie.MovieProc")
  private MovieProcInter movieProc; 
  
  public MovieCont() { 
    //System.out.println("--> MovieCont Created...");
  }  
  
  //index �ڽ����ǽ� ��� ajax
  @ResponseBody
  @RequestMapping(value="/movie/boxlist.do", produces="application/json; charset=utf8", method=RequestMethod.GET)
  public ResponseEntity boxlist() {
    //ResponseEntity : ���� + ���� �ڵ�
    
    //System.out.println("index �ڽ����ǽ�"); 
    
    HttpHeaders responseHeaders = new HttpHeaders();

    List<BoxOfficeVO> boxOffice_List = movieProc.boxOffice_List();
    
    JSONArray json = new JSONArray(boxOffice_List);

    return new ResponseEntity(json.toString(), responseHeaders, HttpStatus.CREATED);
  }
  
/*  //�ε��� �ڽ����ǽ�(rank) json
  @ResponseBody
  @RequestMapping(value="/movie/boxlist.do", produces="application/json; charset=utf8", method=RequestMethod.GET)
  public ResponseEntity boxlist() {
    //ResponseEntity : ���� + ���� �ڵ�
    
    //System.out.println("index �ڽ����ǽ�"); 
    
    HttpHeaders responseHeaders = new HttpHeaders();

    List<MainBoxOfficeVO> mainbo_list = movieProc.mainbo_list();
    
    JSONArray json = new JSONArray(mainbo_list);

    return new ResponseEntity(json.toString(), responseHeaders, HttpStatus.CREATED);
  }*/
  
  /**
   * import org.json.simple.JSONArray; simple.JSONArray�ʿ�!!!!! ��� JSONArray�����
   * ��ȭ ��� (��ȭ��������ȸ OpenAPI) 
   * @return
   * @throws OpenAPIFault
   * @throws Exception
   */
 /*  
  //http://localhost:9090/movie/movie/create.do
   @RequestMapping(value="/movie/create.do", method=RequestMethod.GET)
   public ModelAndView createmovie() throws OpenAPIFault, Exception {
     ModelAndView mav = new ModelAndView();
     mav.setViewName("/movie");  
     
     // ��ȭ ���������� �ڵ� ����  
    
    //64650��
     String curPage = "2"; //(1~2)
     String itemPerPage = "32479"; //32325
     String movieNm = "";
     String directorNm = "";
     String openStartDt = "";
     String openEndDt = "";
     String prdtStartYear = "";
     String prdtEndYear = "";  
     String repNationCd = "";
     String[] movieTypeCdArr = null;
     
     String key = "e9d0301005f12bb578caf805757ad88f"; //9de93e5e1641da0a8dd8b79af11c0a74
         
     KobisOpenAPIRestService service = new KobisOpenAPIRestService(key);
    
     String movieList = service.getMovieList(true, curPage, itemPerPage, movieNm, directorNm,
         openStartDt, openEndDt, prdtStartYear, prdtEndYear, repNationCd, movieTypeCdArr); 
     //System.out.println(movieList);
     
     movieList = "{" + movieList.substring(54, movieList.length()-2);
     //System.out.println(movieList);
     
     JSONParser parser = new JSONParser();
     JSONObject jsonobj = (JSONObject)parser.parse(movieList);
     
     JSONArray jsonarray = (JSONArray)jsonobj.get("movieList"); 
     
     MovieVO movieVO = new MovieVO();  
     for (int i=0 ; i < jsonarray.size() ; i++) {
       JSONObject movieInfo = (JSONObject) jsonarray.get(i);
      
       //��ȭ�ڵ�, ��ȭ����, ��ȭ��������, ��������, ���ۿ���, ������, �帣, ����
       movieVO.setMovieCd(movieInfo.get("movieCd").toString());
       movieVO.setMovieNm(movieInfo.get("movieNm").toString());
      
       if( movieInfo.get("movieNmEn") != null) {
         movieVO.setMovieNmEn(movieInfo.get("movieNmEn").toString());
       } else {
         movieVO.setMovieNmEn("");
       } 
       
       if( movieInfo.get("openDt") != null) {
         movieVO.setOpenDt(movieInfo.get("openDt").toString());
         } else {
           movieVO.setOpenDt(""); 
         }
      
       if( movieInfo.get("prdtYear") != null) {
         movieVO.setPrdtYear(movieInfo.get("prdtYear").toString());
       } else { 
         movieVO.setPrdtYear("");
       }
       movieVO.setRepNationNm(movieInfo.get("nationAlt").toString());
       
       if(movieInfo.get("genreAlt")!= null){
         movieVO.setGenre(movieInfo.get("genreAlt").toString());
       }else{
         movieVO.setGenre("");
       }
       
       if(movieInfo.get("directors")!= null){
         JSONArray jsonarray1 = (JSONArray)movieInfo.get("directors");  
         if(jsonarray1.isEmpty()){
           movieVO.setDirector("");
         }else{
           JSONObject directors = (JSONObject) jsonarray1.get(0);
           String peopleNm = directors.get("peopleNm").toString();
           movieVO.setDirector(peopleNm);
         }
       }else{
         movieVO.setDirector("");
       }
       
       
       movieProc.create(movieVO);
      }  
     System.out.println("json ȣ��2"); 
     // ---------------------------------------------------------------------------------------- 
      //��ȭ ���������� �ڵ� ���� ��   
    
     return mav; 
   } */
   
   /**
    * ��ȭ �߰����� �Է� (��ȭ��������ȸ OpenAPI) 
    * @return
    * @throws OpenAPIFault
    * @throws Exception
    */
  /* 
   //http://localhost:9090/movie/movie/update.do
   @RequestMapping(value="/movie/update.do", method=RequestMethod.GET)
   public ModelAndView updatemovie() throws OpenAPIFault, Exception {
     ModelAndView mav = new ModelAndView();
     mav.setViewName("/movie");  
     
     // ��ȭ ���������� �ڵ� ����  
     MovieVO movieVO = new MovieVO();
      
     String key = "5e16e72330b5f73333cc569f154e0dff"; 
     // e9d0301005f12bb578caf805757ad88f  / 9de93e5e1641da0a8dd8b79af11c0a74 / d4c65a608cd98b2bd81102d1dd45b350
     // 5e16e72330b5f73333cc569f154e0dff  / c10b76fceeea766770018eedd309b999  / 03d5f814faca9ecd5eef8fe5955ac5c9
     KobisOpenAPIRestService service = new KobisOpenAPIRestService(key);
     
       
     // DBMS ( ��� ��ȭ �ڵ� ��ȸ)
     List<MovieVO> codeList = movieProc.codeList();
     //System.out.println("����Ʈ������ : "+ codeList.size());
     
     for (int i = 0; i < codeList.size(); i++) { // ��ȭ �ڵ� �ϳ��� �ݺ� 

       String movieCd = codeList.get(i).getMovieCd();
       String movieList = service.getMovieInfo(true, movieCd); // ��ȭ�ڵ庰 ��ȭ �󼼳��� ��������(��ȭ�ð�, ������)

       //System.out.println("movieList : " + movieList);
       
       if(movieList.length() > 100){
       
       movieList = "{" + movieList.substring(54, movieList.length() - 22); // JSON ���ڿ� ���·� ����

       //System.out.println("select ȣ�� : " + movieList); 

       JSONParser parser = new JSONParser();
       JSONObject movieInfo = (JSONObject) parser.parse(movieList); // ���ڿ� -> JSONObject ��ȯ
       
       //�󿵽ð�
       if(movieInfo.get("showTm") != null) {
         movieVO.setShowTm(movieInfo.get("showTm").toString());
       } else {
         movieVO.setShowTm(""); 
       }
       
       //������
       if (movieInfo.get("audits") != null ) { // audits�� JSONArray �����̹Ƿ� �и��� �ʿ���.
         JSONArray jsonarray = (JSONArray)movieInfo.get("audits");  
         if(jsonarray.isEmpty()){
           movieVO.setWatchGradeNm("");
           }else{
             JSONObject audit = (JSONObject)jsonarray.get(0);  // ����� 1���̹Ƿ� �ε����� '0' ����
             String watchGradeNm = audit.get("watchGradeNm").toString(); 
             movieVO.setWatchGradeNm(watchGradeNm);
           }
         } else {
         movieVO.setWatchGradeNm("");
       }
       
       //���
       if(movieInfo.get("actors")!= null){
         JSONArray jsonarray = (JSONArray)movieInfo.get("actors");  
         if(jsonarray.isEmpty()){
           movieVO.setActors("");
         }else{
           String actor ="";
           for(int j = 0; j<jsonarray.size(); j++){
             JSONObject audit = (JSONObject)jsonarray.get(j); 
             
             if(audit.get("cast").equals(null)  || audit.get("cast").equals("") ){
               actor += audit.get("peopleNm").toString()+", ";
             }else{
               actor += audit.get("peopleNm").toString() + "("+ audit.get("cast")+"), ";
             }
           }
           String actor1 = actor.substring(0, actor.length()-2); 
           movieVO.setActors(actor1);
         }
       }
       
       //25000
       movieVO.setMovieCd(movieCd);
        
       // DBMS (��ȭ �߰����� �Է�)
       movieProc.update(movieVO);
      }
     } 
     System.out.println("�����Ϸ�!");
     return mav; 
   }
   */
  
  /**
   * �ڽ����ǽ� (��ȭ��������ȸ OpenAPI) 
   * @return
   * @throws OpenAPIFault
   * @throws Exception
   */
  /*
   //http://localhost:9090/movie/movie/boxOffice.do
   @RequestMapping(value="/movie/boxOffice.do", method=RequestMethod.GET)
   public ModelAndView create() throws OpenAPIFault, Exception {
     ModelAndView mav = new ModelAndView();
     mav.setViewName("/movie/boxOffice");  
     
  
     // ��ȭ ���������� �ڵ� ����  
    
     String targetDt = "20180729"; //yyyymmdd
     String weekGb = "0"; // 0-�ְ� / 1-�ָ� / 2-����
     String itemPerPage = "10";
     String multiMovieYn = "";
     String repNationCd = "";
     String wideAreaCd = "";
     
     String key = "5e16e72330b5f73333cc569f154e0dff";
         
     KobisOpenAPIRestService service = new KobisOpenAPIRestService(key);
    
     String boxOfficeList = service.getWeeklyBoxOffice(true, targetDt, itemPerPage, weekGb, multiMovieYn, repNationCd, wideAreaCd);
       
     //System.out.println(boxOfficeList);
     
     boxOfficeList = boxOfficeList.substring(19, boxOfficeList.length()-2);
     //System.out.println(boxOfficeList);
     
     JSONParser parser = new JSONParser();
     JSONObject jsonobj = (JSONObject)parser.parse(boxOfficeList);
     
     JSONArray jsonarray = (JSONArray)jsonobj.get("weeklyBoxOfficeList"); 
     
     BoxOfficeVO  boxOfficeVO = new BoxOfficeVO();
     
     for (int i=0 ; i < jsonarray.size() ; i++) {
       JSONObject boxOfficeInfo = (JSONObject) jsonarray.get(i);
       //System.out.println( "boxOfficeInfo "+ i + " : " + boxOfficeInfo);
       
       boxOfficeVO.setMovieCd(boxOfficeInfo.get("movieCd").toString());
       boxOfficeVO.setMovieNm(boxOfficeInfo.get("movieNm").toString());
       boxOfficeVO.setOpenDt(boxOfficeInfo.get("openDt").toString());
       boxOfficeVO.setAudiAcc(boxOfficeInfo.get("audiAcc").toString());
       
       boxOfficeVO.setRank(i+1);
       
       movieProc.boxOffice_update(boxOfficeVO);
     }
     
      
       return mav; 
   } */
   
   // �ڽ����ǽ� ���
   @RequestMapping(value="/movie/mainbo_list.do", method=RequestMethod.GET)
   public ModelAndView mainbo_list() {
     ModelAndView mav = new ModelAndView();
     mav.setViewName("/movie/mainbox_list");  
     
     List<MainBoxOfficeVO> mainbo_list = movieProc.mainbo_list();
     mav.addObject("list", mainbo_list);
     return mav;
   }
   
   //�ε��� �ڽ����ǽ� �׸���
   @RequestMapping(value="/movie/box_grid.do", method=RequestMethod.GET)
   public ModelAndView box_grid() {
     ModelAndView mav = new ModelAndView();
     mav.setViewName("/movie/box_grid");  
     
     List<MainBoxOfficeVO> mainbo_list = movieProc.mainbo_list();
     mav.addObject("list", mainbo_list);
     return mav;
   }
   
  
   //��ȭ �󼼺���
   @RequestMapping(value="/movie/read.do", method=RequestMethod.GET)
   public ModelAndView read(String movieCd, HttpSession session)  {
     ModelAndView mav = new ModelAndView();
     mav.setViewName("/movie_detail/movie_detail");  
     
     //System.out.println("movie/read.do"); 
     MovieVO movieVO = movieProc.a_movieread(movieCd);
     mav.addObject("movieVO",movieVO);
     
     //��ȭ�� ��� ����
     int mcc = movieProc.mcc(movieCd);
     mav.addObject("mcc", mcc);
     
     //��ȭ�� ��� ���� ��
     if(mcc != 0){
       int sum = movieProc.grade_sum(movieCd);
       
       //��ȭ�� ȸ���� ���� ���
       double avg_grade = (double)sum/(double)mcc; 
       mav.addObject("avg_grade", avg_grade);
     }
     
     
     /*���ƿ� ����*/
     int like_count = movieProc.like_count(movieCd);
     mav.addObject("like_count", like_count);
     
     /*���ƿ� üũ Ȯ��*/
     int memberno = (int) session.getAttribute("memberno");
     HashMap<String, Object> hashMap = new HashMap<String, Object>();
     hashMap.put("movieCd", movieCd);              
     hashMap.put("memberno", memberno);
     int like_check = movieProc.a_like_count(hashMap);
     if(like_check == 1){ // ���ƿ並 �������� �ִٸ�
       MovielikeVO movielikeVO = movieProc.like_read(hashMap);
       like_check = movielikeVO.getLike_check();
     }else{
       like_check = 0;
     }
     mav.addObject("like_check", like_check);
     /*���ƿ� üũ Ȯ�� ��*/

     //ȸ���� ��ȭ ��� ���� Ȯ��(�ѹ��� ���� �ϱ� ����)
     int mc_check = movieProc.mc_check(hashMap);
     mav.addObject("mc_check",mc_check);
     
     
     
     String actors = movieVO.getActors();
     //System.out.println(actors); 
     
     if(movieVO.getActors()!= "null"){
       String actor1[] = actors.split(", ");
       mav.addObject("actor", actor1); 
     }

     
   /*  for(String s:actor){
       System.out.println(s);  
       mav.addObject("actor" , s);
     }  */
     return mav;
   }  
   
 //��ȭ ����;��
   @ResponseBody
   @RequestMapping(value="/movie/like.do", produces="application/json; charset=utf8", method=RequestMethod.GET)
   public String like(String movieCd, HttpSession session){
     
     int memberno = (int) session.getAttribute("memberno");
     JSONObject obj = new JSONObject();
     
     ArrayList<String> msgs = new ArrayList<String>();
     HashMap<String, Object> hashMap = new HashMap<String, Object>();
     hashMap.put("movieCd", movieCd);
     hashMap.put("memberno", memberno);
       
     int like_check = 0;
     
     if(movieProc.a_like_count(hashMap) == 0){ // �ش翵ȭ�� ���ƿ� ó�� �����ٸ�
       movieProc.like_create(hashMap); // ���ƿ� ����
     }else{ // �ش翵ȭ�� ���ƿ� �������� �ִٸ�
       MovielikeVO movielikeVO = movieProc.like_read(hashMap);
       
       if(movielikeVO.getLike_check() == 1){ // ���
         movieProc.like_check_cancel(hashMap);
         like_check = 0;
       }else if(movielikeVO.getLike_check() == 0){
         movieProc.like_check(hashMap);
         like_check = 1;
       }
     }
     
     int like_count = movieProc.like_count(movieCd);
     
     obj.put("like_count", like_count);
     obj.put("like_check", like_check);
     
     return obj.toString();
    }
  
  //������ ��ȭ ���(��ü ����¡x)
  @RequestMapping(value="/movie/a_movielist.do", method=RequestMethod.GET)
  public ModelAndView a_movielist()  {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/movie/list");  
    
    List<MovieVO> list = movieProc.a_movielist();
    
    mav.addObject("list",list);
    
    return mav;
  }
  
  //������ ��ȭ �� �� ��ȸ
  @RequestMapping(value="/movie/a_movieread.do", method=RequestMethod.GET)
  public ModelAndView a_movieread(String movieCd)  {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/movie/a_read");  
    
    MovieVO movieVO = movieProc.a_movieread(movieCd);
    mav.addObject("movieVO",movieVO);
    
    return mav;
  }
  
//��ȭ ���� ��
  @RequestMapping(value="/movie/a_movieupdate.do", method = RequestMethod.GET)
 public ModelAndView update(String movieCd) {
   ModelAndView mav = new ModelAndView();
   mav.setViewName("/movie/a_update"); 

   MovieVO movieVO = movieProc.a_movieupdate(movieCd);
   mav.addObject("movieVO", movieVO);

   return mav;
 }
  
  //������ ��ȭ ���� ó��
  @RequestMapping(value="/movie/a_movieupdate.do", method=RequestMethod.POST)
  public ModelAndView a_movieupdate(HttpServletRequest request, MovieVO movieVO)  {
  
    //System.out.println("--> a_movieupdate.do!!!!");
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/movie/a_message");
    
    ArrayList<String> msgs = new ArrayList<String>();
    ArrayList<String> links = new ArrayList<String>();
    
    String movieCd = movieVO.getMovieCd();
    
    // -----------------------------���� ����----------------------------------------------
    
   /* <input type="file" class="form-control input-lg" name='moiveImgMF' id='moiveImgMF' >
    ��
     name='moiveImgMF'�� �ش��ϴ� �ʵ带 ã�Ƽ� File ��ü�� �ڵ����� �Ҵ�
    ��
    MovieVO.java: private MultipartFile moiveImgMF;
    ��
     ���� ��ü ���: MultipartFile moiveImgMF = movieVO.getMoiveImgMF();          
     */
    
    String upDir = Tool.getRealPath(request, "/movie/storage");
    MultipartFile movieImgMF = movieVO.getMovieImgMF();
    String movieImg = "";                    // DBMS moiveImg �÷��� ��
    long imgSize = movieImgMF.getSize(); // ���� ũ��
    String thumb = "";                 // DBMS thumb �÷��� ��
     
    // ������ ��ϵ� �� ���� �ε�
    MovieVO movieVO_old = movieProc.a_movieread(movieVO.getMovieCd());
    
    //System.out.println("movieVO_old" + movieVO_old);
    
   if (imgSize > 0) { // ��ϵ� ������ �ִٸ�
     Tool.deleteFile(movieVO_old.getMovieImg());
     Tool.deleteFile(movieVO_old.getThumb());
     Tool.deleteFile(upDir, movieVO_old.getMovieImg());    // ���� ���� ����
     Tool.deleteFile(upDir, movieVO_old.getThumb()); // ���� Thumb ���� ����
    
     movieImg = Upload.saveFileSpring(movieImgMF, upDir); // �ű� ���� ���ε�
     
     if (Tool.isImage(movieImg)) { // ���ο� ���� �̹������� �˻�
       thumb = Tool.preview(upDir, movieImg, 245, 370); // Thumb �̹��� ����
     } 
    } else {
      // ������ �������� �ʴ� ��� ���� ���� ���� ���
      movieImg = movieVO_old.getMovieImg();
      imgSize = movieVO_old.getImgSize();
      thumb = movieVO_old.getThumb();
    } 
     
    movieVO.setMovieImg(movieImg);
    movieVO.setImgSize(imgSize);
    movieVO.setThumb(thumb);
 // -----------------------------���� ���� ����----------------------------------------------
    
    if (movieProc.a_movieupdate(movieVO) == 1) {
      msgs.add("���� �����߽��ϴ�.");
      links.add("<button type='button' onclick=\"location.href='./a_movieread.do?movieCd="+movieCd+"'\">���� Ȯ��</button>");
      
    } else {
      msgs.add("�� ���濡 �����߽��ϴ�.");
      msgs.add("�˼������� �ٽ� �� �� �õ����ּ���.");
      links.add("<button type='button' onclick=\"history.back()\">�ٽ� �õ�</button>");
       
    }
    links.add("<button type='button' onclick=\"location.href='./a_movielist_search.do'\">���</button>");
    
    mav.addObject("msgs", msgs);
    mav.addObject("links", links);
    
    return mav;
    
  }  
  
  //������ ) ��� + �˻�(��ȭ����) + ����¡
  @RequestMapping(value = "/movie/a_movielist_search.do", method = RequestMethod.GET)
  public ModelAndView a_list_search(
      @RequestParam(value="word", defaultValue="") String word,
      @RequestParam(value="nowPage", defaultValue="1") int nowPage
      ) { 
    //System.out.println("--> a_list_search.do GET called.");
    //System.out.println("--> nowPage: " + nowPage);
    
    ModelAndView mav = new ModelAndView();
     
    // �˻� ��� �߰�
    mav.setViewName("/movie/a_list_search");   
    
    // ���ڿ� ���ڿ� Ÿ���� �����ؾ������� Object ���
    HashMap<String, Object> hashMap = new HashMap<String, Object>();
    hashMap.put("word", word);              
    hashMap.put("nowPage", nowPage);       
    
    // �˻� ���
    List<MovieVO> list = movieProc.a_list_search(hashMap);
    //System.out.println( "����Ʈ ������ : "+list.size());
    mav.addObject("list", list);
      
    // �˻��� ���ڵ� ����
    int asearch_count = movieProc.asearch_count(hashMap);
    mav.addObject("asearch_count", asearch_count);


    /*
     * SPAN�±׸� �̿��� �ڽ� ���� ����, 1 ���������� ���� 
     * ���� ������: 11 / 22   [����] 11 12 13 14 15 16 17 18 19 20 [����] 
     *
     * @param search_count �˻�(��ü) ���ڵ�� 
     * @param nowPage     ���� ������
     * @param word �˻���
     * @return ����¡ ���� ���ڿ�
     */ 
    String paging = movieProc.apaging(asearch_count, nowPage, word);
    mav.addObject("paging", paging);
 
    mav.addObject("nowPage", nowPage);
    
    return mav;
  }
  
  //���θ޴� ) ��� + �˻�(��ȭ����) + ����¡
  @RequestMapping(value = "/movie/main_movie.do", method = RequestMethod.GET)
  public ModelAndView main_movie(
      @RequestParam(value="word", defaultValue="") String word,
      @RequestParam(value="nowPage", defaultValue="1") int nowPage,
      @RequestParam(value="col", defaultValue="") String col
      ) { 
    //System.out.println("--> a_list_search.do GET called.");
    //System.out.println("--> nowPage: " + nowPage);
    
    ModelAndView mav = new ModelAndView();
     
    // �˻� ��� �߰�
    mav.setViewName("/movie/main_movie");   
    
    // ���ڿ� ���ڿ� Ÿ���� �����ؾ������� Object ���
    HashMap<String, Object> hashMap = new HashMap<String, Object>();
    hashMap.put("word", word);              
    hashMap.put("nowPage", nowPage);       
    hashMap.put("col", col);       
    
    // �˻� ���
    List<MovieVO> list = movieProc.main_movie(hashMap);
    //System.out.println( "����Ʈ ������ : "+list.size());
    mav.addObject("list", list);
      
    // �˻��� ���ڵ� ����
    int search_count = movieProc.search_count(hashMap);
    mav.addObject("search_count", search_count);


    /*
     * SPAN�±׸� �̿��� �ڽ� ���� ����, 1 ���������� ���� 
     * ���� ������: 11 / 22   [����] 11 12 13 14 15 16 17 18 19 20 [����] 
     *
     * @param search_count �˻�(��ü) ���ڵ�� 
     * @param nowPage     ���� ������
     * @param word �˻���
     * @return ����¡ ���� ���ڿ�
     */ 
    String paging = movieProc.paging(search_count, nowPage, col, word);
    mav.addObject("paging", paging);
 
    mav.addObject("nowPage", nowPage);
    
    return mav;
  }
  
//�帣�޴� ) ��� + �˻�(��ȭ����) + ����¡
  @RequestMapping(value = "/movie/main_movie_genre.do", method=RequestMethod.GET)
  public ModelAndView main_movie_genre(
      @RequestParam(value="nowPage", defaultValue="1") int nowPage,
      String genre) { 
    
    //System.out.println("�帣 controller�� ����");
    ModelAndView mav = new ModelAndView();
     
    // �˻� ��� �߰�
    mav.setViewName("/movie/main_movie_genre");   
    
    // ���ڿ� ���ڿ� Ÿ���� �����ؾ������� Object ���
    HashMap<String, Object> hashMap = new HashMap<String, Object>();
    hashMap.put("genre", genre);              
    hashMap.put("nowPage", nowPage);       
    
    // �˻� ���
    List<MovieVO> list = movieProc.list_search_genre(hashMap);
    //System.out.println( "����Ʈ ������ : "+list.size());
    mav.addObject("list", list);
      
    //System.out.println("�˻��ϰ� ����.");
    
    // �˻��� ���ڵ� ����
    int search_count = movieProc.search_count_genre(hashMap);
    mav.addObject("search_count", search_count);

    //System.out.println("search_count : "+ search_count);
    
    /*
     * SPAN�±׸� �̿��� �ڽ� ���� ����, 1 ���������� ���� 
     * ���� ������: 11 / 22   [����] 11 12 13 14 15 16 17 18 19 20 [����] 
     *
     * @param search_count �˻�(��ü) ���ڵ�� 
     * @param nowPage     ���� ������
     * @param word �˻���
     * @return ����¡ ���� ���ڿ�*/
      
    String paging = movieProc.paging_genre(search_count, nowPage, genre);
    mav.addObject("paging", paging);
 
    mav.addObject("nowPage", nowPage);
    
    return mav;
  }
  
  
  //ȸ���� ����;�� ����¡
  @RequestMapping(value = "/movie/moviebucket.do", method = RequestMethod.GET)
  public ModelAndView moviebucket(
      @RequestParam(value="word", defaultValue="") String word,
      @RequestParam(value="nowPage", defaultValue="1") int nowPage,
      HttpSession session
      ) { 
    //System.out.println("--> a_list_search.do GET called.");
    //System.out.println("--> nowPage: " + nowPage);
    int memberno = (int) session.getAttribute("memberno");
    
    ModelAndView mav = new ModelAndView();
     
    // �˻� ��� �߰�
    mav.setViewName("/movie/m_moviebucket");   
    
    // ���ڿ� ���ڿ� Ÿ���� �����ؾ������� Object ���
    HashMap<String, Object> hashMap = new HashMap<String, Object>();
    hashMap.put("word", word);              
    hashMap.put("nowPage", nowPage);    
    hashMap.put("memberno", memberno);  
    
    // �˻� ���
    List<MoviebucketVO> list = movieProc.moviebucket(hashMap);
    mav.addObject("list", list);
      
    // �˻��� ���ڵ� ����
    int mbc = movieProc.mbc(hashMap);
    mav.addObject("mbc", mbc);


    /*
     * SPAN�±׸� �̿��� �ڽ� ���� ����, 1 ���������� ���� 
     * ���� ������: 11 / 22   [����] 11 12 13 14 15 16 17 18 19 20 [����] 
     *
     * @param search_count �˻�(��ü) ���ڵ�� 
     * @param nowPage     ���� ������
     * @param word �˻���
     * @return ����¡ ���� ���ڿ�
     */ 
    String paging = movieProc.apaging(mbc, nowPage, word);
    mav.addObject("paging", paging);
 
    mav.addObject("nowPage", nowPage);
    
    return mav;
  }

  
}
