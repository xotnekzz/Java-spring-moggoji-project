package dev.mvc.director;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;


@Component("dev.mvc.director.DirectorProc")
public class DirectorProc implements DirectorProcInter{
    
    @Autowired
    @Qualifier("dev.mvc.director.DirectorDAO")
    private DirectorDAOInter directorDAO;
    
    @Override // ȸ�� ���. ( ���� �� 1 , ���� �� 0 ��ȯ ) 
    public int create(DirectorVO directorVO) {
        int count = directorDAO.create(directorVO);
        return count;
    }
    
}
