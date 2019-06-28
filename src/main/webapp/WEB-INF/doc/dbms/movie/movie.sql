SELECT * FROM USER_OBJECTS WHERE OBJECT_TYPE='TABLE';


1) ���̺� ����

DROP TABLE movie;
DROP TABLE movie CASCADE CONSTRAINTS;


2) ���̺� ����
/**********************************/
/* Table Name: ��ȭ */
/**********************************/
CREATE TABLE movie(
    movieCd                           VARCHAR2(1000)    NOT NULL,
    movieno                           NUMBER     		NOT NULL,
    movieNm                           VARCHAR2(300)     NULL ,
    movieNmEn                         VARCHAR2(300)     NULL ,
    prdtYear                          VARCHAR2(300)     NULL ,
    openDt                            VARCHAR2(300)     NULL ,
    repNationNm                       VARCHAR2(300)     NULL ,
    plot                              CLOB   			NULL ,
    showTm                            VARCHAR2(100)     NULL ,
    watchGradeNm                      VARCHAR2(300)     NULL ,
    keyword                           VARCHAR2(1000)    NULL ,
    movieImg                          VARCHAR2(300)     NULL ,
    thumb                             VARCHAR2(300)     NULL ,
    imgSize                           NUMBER(9)   		DEFAULT 0  NULL,
    vodclass                          VARCHAR2(2000)    NULL,   
    genre               			  VARCHAR2(100) 	NULL,
    director             			  VARCHAR2(100) 	NULL,
    actors               			  VARCHAR2(4000) 	NULL,
    PRIMARY KEY(movieCd)
);

COMMENT ON TABLE movie is '��ȭ';
COMMENT ON COLUMN movie.movieCd is '��ȭ�ڵ�';
COMMENT ON COLUMN movie.movieno is '��ȭ��ȣ';
COMMENT ON COLUMN movie.movieNm is '��ȭ��';
COMMENT ON COLUMN movie.movieNmEn is '��ȭ������';
COMMENT ON COLUMN movie.prdtYear is '���ۿ���';
COMMENT ON COLUMN movie.openDt is '��������';
COMMENT ON COLUMN movie.repNationNm is '������';
COMMENT ON COLUMN movie.plot is '�ٰŸ�';
COMMENT ON COLUMN movie.showTm is '�󿵽ð�';
COMMENT ON COLUMN movie.watchGradeNm is '��ǥ�������';
COMMENT ON COLUMN movie.keyword is 'Ű����';
COMMENT ON COLUMN movie.movieImg is '��ȭ�̹���';
COMMENT ON COLUMN movie.thumb is '�����';
COMMENT ON COLUMN movie.imgSize is '�̹���������';
COMMENT ON COLUMN movie.genre is '�帣';
COMMENT ON COLUMN movie.director is '����';
COMMENT ON COLUMN movie.actors is '����';


select * from movie;

-- ��ü ���
SELECT movieCd, movieno, movieNm, movieNmEn, prdtYear, openDt, repNationNm, 
          plot, showTm, watchGradeNm,
          keyword, movieImg, thumb, imgSize, vodclass, genre, director, actors
FROM movie
ORDER BY movieno ASC;

-- �˻�
SELECT movieCd, movieno, movieNm, movieNmEn, prdtYear, openDt, repNationNm, 
          plot, showTm, watchGradeNm,
          keyword, movieImg, thumb, imgSize, vodclass, genre, director, actors
FROM movie
WHERE movieNm LIKE '%��ũ������%'
ORDER BY movieno ASC;

-- �� �� ��ȸ
SELECT movieCd, movieno, movieNm, movieNmEn, prdtYear, openDt, repNationNm, 
          plot, showTm, watchGradeNm,
          keyword, movieImg, thumb, imgSize, vodclass, genre, director, actors
FROM movie
WHERE movieCd = '20148879';


-- ����
update movie 
set plot = '',  keyword='', movieImg='', 
      imgSize=0, thumb='', vodclass=''
WHERE movieno = 1;

-- ����
UPDATE movie
SET plot = '�ٰŸ� �ٰŸ�',  keyword='#��γ�', movieImg='post.jpg', 
      imgSize=1000, thumb='poster_t.jpg', vodclass='������'
WHERE movieCd = '20184383';


-- ��ü ����
DELETE FROM movie;

-- ��ȸ
select * from movie;

SELECT * FROM movie
WHERE movieNm='����';

-- �ں� ��ȭ ������ �� ��������
select movieCd
from movie
where movieno BETWEEN 295 AND 2700

-- ������ �˻�
 select movieno, movieCd, actors
 from movie
 where movieno BETWEEN 290 AND 300;
 
-- ���� ����
SELECT count(*)
FROM movie;

-- �˻��� ���� ����
SELECT COUNT(*) as cnt
FROM movie
WHERE movieNm like '%�ѳ�%';


-- �˻�, ����¡
SELECT movieCd, movieno, movieNm, movieNmEn, prdtYear, openDt, 
          repNationNm, plot, showTm, watchGradeNm,
          keyword, movieImg, thumb, imgSize, vodclass, r
FROM (
          SELECT movieCd, movieno, movieNm, movieNmEn, prdtYear, openDt, 
                    repNationNm, plot, showTm, watchGradeNm,
                    keyword, movieImg, thumb, imgSize, vodclass, rownum as r
          FROM (
                    SELECT movieCd, movieno, movieNm, movieNmEn, prdtYear, openDt, 
                              repNationNm, plot, showTm, watchGradeNm,
                              keyword, movieImg, thumb, imgSize, vodclass
                     FROM movie
                     WHERE movieNm LIKE '%�ѳ�%'
                     ORDER BY movieno ASC
          )
)
WHERE r >=1 AND R<=20;

-- ��ȭ�ڵ� Ȯ��
SELECT movieCd
FROM movie;
    
-- ��ȭ�ڵ庰 �ڸ�Ʈ ����
  SELECT count(*)
  FROM mcomment
  WHERE movieCd=(SELECT movieCd FROM movie WHERE movieCd='20171841')  AND memberno = 2
  
  
-- �� ��ȭ�� ���� ���� 

SELECT sum(grade)
FROM mcomment
WHERE movieCd =(select movieCd from movie where movieCd='20171841')
  

-- ���� �� ����
SELECT sum(GRADE)
FROM mcomment;

-- 64958 -> 20400
 delete from movie
 where openDt is null;
 
 
 select count(*)
 from movie
 where actors is null;
 
  delete from movie
 where actors is null;
 
 select watchGradeNm
 from movie
 where movieno = 10363;
  
 
 select *
 from movie
 where movieNm = '%�̼�%';