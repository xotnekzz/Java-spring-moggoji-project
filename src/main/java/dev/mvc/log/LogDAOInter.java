package dev.mvc.log;

public interface LogDAOInter {
  
  /**
   * �α� ����
   * <Xmp>
   * <insert id="log_create" parameterType="LogVO">
   * </Xmp>
   * @return
   */
  public int log_create(LogVO logVO);
  
  
}
