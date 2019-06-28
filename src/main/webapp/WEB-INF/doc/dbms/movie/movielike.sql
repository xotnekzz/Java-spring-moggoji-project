SELECT * FROM USER_OBJECTS WHERE OBJECT_TYPE='TABLE';


DROP TABLE MOVIELIKE;

CREATE TABLE movielike(
  likeno        NUMBER(7)        NOT NULL,
  movieCd     VARCHAR2(1000)    NOT NULL,
  memberno  NUMBER(7)        NOT NULL,
  like_check    NUMBER(5) DEFAULT 0 NULL,
  PRIMARY KEY (likeno),
  FOREIGN KEY (movieCd) REFERENCES MOVIE (movieCd),
  FOREIGN KEY (memberno) REFERENCES MEMBER (memberno)
);

COMMENT ON TABLE movielike is '����;��';
COMMENT ON COLUMN movielike.likeno is '���ƿ� ��ȣ';
COMMENT ON COLUMN movielike.movieCd is '��ȭ �ڵ�';
COMMENT ON COLUMN movielike.memberno is 'ȸ�� ��ȣ';
COMMENT ON COLUMN movielike.like_check is '����;�� üũ Ȯ��';


select * from movielike;

select likeno from movielike where mno =1;

select count(*)
from movielike;

-- ���ƿ� ���� (��������)
INSERT INTO movielike(likeno, movieCd, memberno, like_check)
VALUES((SELECT NVL(MAX(likeno), 0)+1 as likeno FROM movielike), '20171841', 1, 0);

-- ���ƿ並 ������ ��
UPDATE movielike
SET like_check = like_check+1
WHERE memberno = 1 AND movieCd = '20171841';

-- ���ƿ� ���
UPDATE movielike
SET like_check = 0
WHERE memberno = 1 AND movieCd = '20171841';

-- read
SELECT likeno, movieCd, memberno, like_check
FROM movielike
 WHERE memberno = 1 AND movieCd='20171841'
 
 --��ȭ�� ���ƿ� ����
SELECT count(*)
FROM movielike
WHERE movieCd = #{movieCd} AND like_check = 1

-- ȸ���� ��ȭ�� ���ƿ� üũ Ȯ��
SELECT count(*)
FROM movielike
WHERE movieCd='20171841' and memberno = 1;

-- ȸ�� ����;�� �˻� ī��Ʈ
SELECT count(*)
FROM movie m, movielike ml
WHERE ml.like_check =1 AND m.movieCd =ml.movieCd AND ml.memberno = 2 AND m.movieNm LIKE '%��%';

-- ȸ�� ����;�� ���
    SELECT movieNm, thumb, likeno, movieCd r
    FROM(
             SELECT movieNm, thumb, likeno, movieCd, rownum as r
             FROM(
                     SELECT m.movieNm, m.thumb, m.movieCd, ml.likeno
                     FROM movie m, movielike ml
                     WHERE ml.like_check = 1  AND m.movieCd =ml.movieCd AND ml.memberno = 2 AND m.movieNm LIKE'%��%'
                     ORDER BY likeno DESC
                    )
       )
WHERE  r >=1 AND R<=20;



