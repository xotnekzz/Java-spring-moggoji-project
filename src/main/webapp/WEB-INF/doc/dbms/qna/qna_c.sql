1. ���̺� ����.

DROP TABLE qna;

CREATE TABLE qna (
    qnano               NUMBER                NOT NULL,
    seqno               NUMBER                default 1             NOT NULL, -- 0-������ / 1-�Ϲݱ�
    qnatype             VARCHAR2(20)          NOT NULL,
    title               VARCHAR2(100)         NOT NULL,
    content             CLOB   			      NOT NULL,
    memberno            NUMBER                NOT NULL,
    mname               VARCHAR2(20)          NOT NULL,
    cnt                 NUMBER                default 0             NOT NULL,
    rdate               DATE                  NOT NULL,
    qpasswd    			VARCHAR2(20)          NULL,
    statement           NUMBER          	  NOT NULL,        		-- 0-ó���� / 1- ó���Ϸ�   
  	indent   			NUMBER        		  DEFAULT 0       	 	NOT NULL,
  	ansnum   			NUMBER       		  DEFAULT 0       		NOT NULL,
    primary key ( qnano ),
    foreign key ( memberno ) references member ( memberno )
);



COMMENT ON TABLE qna is '�����Խ���';
COMMENT ON COLUMN qna.qnano is '���� �Խñ� ��ȣ';
COMMENT ON COLUMN qna.seqno is '�����۰� �Ϲݱ� ������ seqno';
COMMENT ON COLUMN qna.qnatype is '���� ����';
COMMENT ON COLUMN qna.title is '���� ����';
COMMENT ON COLUMN qna.content is '���� ����';
COMMENT ON COLUMN qna.memberno is 'ȸ�� ��ȣ';
COMMENT ON COLUMN qna.mname is 'ȸ�� �̸�';
COMMENT ON COLUMN qna.cnt is '���� �Խñ� ��ȸ ��';
COMMENT ON COLUMN qna.rdate is '�Խ� ��¥ �� �ð� ';
COMMENT ON COLUMN qna.qpasswd is '���� �Խñ� ��й�ȣ';
COMMENT ON COLUMN qna.statement is '��� ����';
COMMENT ON COLUMN qna.indent is '�亯 ����';
COMMENT ON COLUMN qna.ansnum is '�亯 ����';


2. ������ ����

INSERT INTO qna(qnano, seqno, qnatype, title, content, memberno, mname, cnt, rdate, qpasswd, statement, indent, ansnum)
        values((select NVL(MAX(qnano),0 ) +1 as qnano from qna),
                        2, '��ȭ��õ2', '������ ��õ�մϴ�2', '������ ��õ�մϴ� �÷��ּ���.2' , 2, 'james9957' , 0, sysdate, '1234', 0,0,0);
                        
           select * from qna;  
        
        
             
3. ��� ���

select mact
from member
where memberno = (select memberno from qna where memberno=1);

select mact
from member
where memberno=1;

-- ������ �Ϲݱ� ����
select * from qna
order by seqno DESC, qnano DESC;

SELECT qnano, seqno, qnatype, title, content, memberno, mname, cnt, rdate, qpasswd, statement, indent, ansnum
FROM qna
WHERE qnano = 1;

select qnano, qnatype, title, contents, memberno, cnt, rdate, statement from qna order by seqno asc , qnano asc;

---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
     4 ����      �� �ۺ��� ä��            ä���������Դϴ�.                                   admin               2018-06-08 16:19:53.0       ó����
     1 ����      ��й�ȣ�� �н�             ��й�ȣ�� ã���ּ���                             james9957         2018-06-08 16:19:50.0       ó����
     2 ��ȭ      ��ȭ ���� ���� ����        �ֿ� ��� �̸��� �߸� ǥ��Ǿ����ϴ�.       james9957         2018-06-08 16:19:51.0       ó����
     3 ��Ÿ      ������ ä��                   ���� �����ڴ� ���� ä����� ������?          james9957         2018-06-08 16:19:52.0       ó����


select qnano, qnatype, title, contents, memberno, cnt, rdate, statement from qna where qnatype='����' or qnatype='����' order by seqno asc , qnano asc;

---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

     4 ����      �� �ۺ��� ä��        ä���������Դϴ�.                 admin                 2018-06-08 16:19:53.0               ó����
     1 ����      ��й�ȣ�� �н�         ��й�ȣ�� ã���ּ���           james9957           2018-06-08 16:19:50.0               ó����


select count(*) as cnt
from qna
where qnano like '%1%';

4. ���̺� ����

-- ��ȸ�� ����
UPDATE qna
SET cnt = cnt + 1
WHERE qnano = 1;

-- ���� ���� ����
UPDATE qna
SET title='Ÿ��Ʋ', content='������', qpasswd='1234'
WHERE qnano = 2;

update qna
set seqno = 2
where qnano = 1;

UPDATE qna
SET title='���̵� �н�' , contents='��й�ȣ�� �ƴ϶� ���̵� �Ҿ���Ƚ��ϴ�.' , statement='�亯 �Ϸ�'
WHERE qnano = 1;

select qnano, qnatype, title, contents, memberno, cnt, rdate, statement from qna order by seqno asc , qnano asc;

---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

     4 ����      �� �ۺ��� ä��   ä���������Դϴ�.             admin 2018-06-08 16:19:53.0 ó����
     1 ����      ���̵� �н�      ��й�ȣ�� �ƴ϶� ���̵� �Ҿ���Ƚ��ϴ�. james9957 2018-06-08 16:19:50.0 �亯 �Ϸ�
     2 ��ȭ      ��ȭ ���� ���� ���� �ֿ� ��� �̸��� �߸� ǥ��Ǿ����ϴ�. james9957 2018-06-08 16:19:51.0 ó����
     3 ��Ÿ      ������ ä��      ���� �����ڴ� ���� ä����� ������?  james9957 2018-06-08 16:19:52.0 ó����

     
5. ����

delete from qna where qnano = 2;

select qnano, qnatype, title, contents, memberno , cnt,  rdate, statement from qna order by seqno asc , qnano asc;

---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

     4 ����      �� �ۺ��� ä��   ä���������Դϴ�.             admin 2018-06-08 16:19:53.0 ó����
     1 ����      ���̵� �н�      ��й�ȣ�� �ƴ϶� ���̵� �Ҿ���Ƚ��ϴ�. james9957 2018-06-08 16:19:50.0 �亯 �Ϸ�
     3 ��Ÿ      ������ ä��      ���� �����ڴ� ���� ä����� ������?  james9957 2018-06-08 16:19:52.0 ó����

 
6. ��ȸ

select qnano, qnatype, title, contents, memberno , cnt, rdate, statement from qna where memberno='4' order by seqno asc, qnano asc;

---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

     1 ����      ���̵� �н�      ��й�ȣ�� �ƴ϶� ���̵� �Ҿ���Ƚ��ϴ�. james9957   0 2018-06-08 16:19:50.0 �亯 �Ϸ�
     3 ��Ÿ      ������ ä��      ���� �����ڴ� ���� ä����� ������?  james9957   0 2018-06-08 16:19:52.0 ó����
     
     
     

select * from qna;

