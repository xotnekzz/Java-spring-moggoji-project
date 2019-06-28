drop table log;

create table log(
  logno     NUMBER    NOT NULL,
  memail    VARCHAR2(100)     DEFAULT 'guest'  NOT NULL,
  ip            VARCHAR(15)                 NOT NULL, 
  uri         VARCHAR(100)                 NOT NULL, -- URI �ּ�
  logdate   VARCHAR(19)                 NOT NULL, -- ���� ��¥
  PRIMARY KEY(logno)
);

COMMENT ON TABLE log is '�α�';
COMMENT ON COLUMN log.logno is '�α� ��ȣ';
COMMENT ON COLUMN log.memail is 'ȸ�� ����';
COMMENT ON COLUMN log.ip is 'ip';
COMMENT ON COLUMN log.uri is 'URI �ּ�';
COMMENT ON COLUMN log.logdate is '���� ��¥';


INSERT INTO log(logno, memail, ip, uri, logdate)
VALUES(SELECT NVL(MAX(logno), 0)+1 AS logno FROM log), 'test@naver.com', '10.0.0.1', '/index', sysdate());

select memail, ip, uri, logdate
from log
order by logdate desc;

