1) ���̺� ����
/**********************************/
/* Table Name: �帣 */
/**********************************/
CREATE TABLE genre(
    genreno                           NUMBER    NOT NULL,
    genreNm                           VARCHAR2(30)     NOT NULL,
    PRIMARY KEY (genreno)
);

COMMENT ON TABLE genre is '�帣'; 
COMMENT ON COLUMN genre.genreno is '�帣��ȣ';
COMMENT ON COLUMN genre.genreNm is '�帣��';

2) ���̺� ����

DROP TABLE genre;

3) �帣 �߰�
INSERT INTO genre(genreno, genreNm) 
VALUES((SELECT NVL(MAX(genreno), 0) + 1 as genreno FROM genre), '���');

INSERT INTO genre(genreno, genreNm) 
VALUES((SELECT NVL(MAX(genreno), 0) + 1 as genreno FROM genre), '�׼�');

INSERT INTO genre(genreno, genreNm) 
VALUES((SELECT NVL(MAX(genreno), 0) + 1 as genreno FROM genre), '�ڹ̵�');

INSERT INTO genre(genreno, genreNm) 
VALUES((SELECT NVL(MAX(genreno), 0) + 1 as genreno FROM genre), 'ȣ��');

INSERT INTO genre(genreno, genreNm) 
VALUES((SELECT NVL(MAX(genreno), 0) + 1 as genreno FROM genre), '����');

INSERT INTO genre(genreno, genreNm) 
VALUES((SELECT NVL(MAX(genreno), 0) + 1 as genreno FROM genre), '������');

INSERT INTO genre(genreno, genreNm) 
VALUES((SELECT NVL(MAX(genreno), 0) + 1 as genreno FROM genre), 'SF');

INSERT INTO genre(genreno, genreNm) 
VALUES((SELECT NVL(MAX(genreno), 0) + 1 as genreno FROM genre), '��ť���͸�');

INSERT INTO genre(genreno, genreNm) 
VALUES((SELECT NVL(MAX(genreno), 0) + 1 as genreno FROM genre), '��Ÿ��');

INSERT INTO genre(genreno, genreNm) 
VALUES((SELECT NVL(MAX(genreno), 0) + 1 as genreno FROM genre), '����');

4) �帣 ���
SELECT * FROM genre;

