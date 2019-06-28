1) ���̺� ����
/**********************************/
/* Table Name: ���� */
/**********************************/
CREATE TABLE director(
    directorno                        NUMBER    NOT NULL,
    directorNm                        VARCHAR2(100)    NOT NULL,
    directorEnNm                      VARCHAR2(100)    NULL,
    dcode                             VARCHAR2(1000)    NOT NULL,
    PRIMARY KEY (dcode) 
);

COMMENT ON TABLE directors is '����';
COMMENT ON COLUMN directors.directorno is '������ȣ';
COMMENT ON COLUMN directors.directorNm is '������';
COMMENT ON COLUMN directors.directorEnNm is '����������';
COMMENT ON COLUMN directors.dcode is '�����ڵ�';

2) ���̺� ����

DROP TABLE director;
 
3) ��� 

INSERT INTO director(directorno, directorNm, directorEnNm, dcode)
VALUES((SELECT NVL(MAX(directorno), 0)+1 as directorno FROM director), '������', 'James', '201231044');

4) ��ȸ

SELECT directorno, directorNm, directorEnNm, dcode
FROM director;
 
SELECT directorno, directorNm, directorEnNm, dcode
FROM director
WHERE  directorno=3000;

SELECT directorno, directorNm, directorEnNm, dcode
FROM director
WHERE  directorNm='����ȣ';

SELECT count(*)
FROM director;


5) ����

DELETE FROM director;