package dev.mvc.log;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

@Controller
public class LogCont {
  
  @Autowired

  @Qualifier("dev.mvc.log.LogProc") // �Ҵ�Ǵ� Class ��ü�� �̸�.
  private LogProcInter logProc;
  
  public LogCont() {
      //System.out.println("LogCont ����Ǿ���.");
  }

}
