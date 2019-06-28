/**********************************/
/* Table Name: ��� */
/**********************************/
CREATE TABLE actor(
    acode                            VARCHAR2(1000)     NOT NULL,
    actorno                          NUMBER    NOT NULL,
    actorNm                         VARCHAR2(100)    NOT NULL,
    actorEnNm                      VARCHAR2(100)    NULL,
    PRIMARY KEY (acode)
);  
 
COMMENT ON TABLE actors is '���';
COMMENT ON COLUMN actors.actorno is '����ȣ';
COMMENT ON COLUMN actors.actorNm is '����';
COMMENT ON COLUMN actors.actorEnNm is '��쿵����';  
COMMENT ON COLUMN actors.acode is '����ڵ�';


2) ���̺� ����
DROP TABLE actor;

3) ���

INSERT INTO actor(actorno, actorNm, actorEnNm, acode)
VALUES((SELECT NVL(MAX(actorno), 0)+1 as actorno FROM actor), '������', 'James', '201231044');

4) ��ȸ
 
SELECT actorno, actorNm, actorEnNm, acode
FROM actor
ORDER BY actorno;
 
SELECT actorno, actorNm, actorEnNm, acode
FROM actor
WHERE  actorno=33700;

SELECT actorno, actorNm, actorEnNm, acode
FROM actor
WHERE  actorNm = '��ȿ��';

SELECT count(*)
FROM actor;


5) ����

DELETE FROM actor;
 