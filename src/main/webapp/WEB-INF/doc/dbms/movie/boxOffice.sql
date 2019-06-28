
DROP TABLE boxOffice;

CREATE TABLE boxOffice(
	rank NUMBER NOT NULL,
	movieCd VARCHAR2(1000) NOT NULL,
	movieNm VARCHAR2(300) NOT NULL,
	openDt VARCHAR2(10) NOT NULL,
	audiAcc VARCHAR2(10) NOT NULL,
	PRIMARY KEY(rank)
);

COMMENT ON TABLE boxOffice is '�ڽ����ǽ�';
COMMENT ON COLUMN boxOffice.rank is '��ȭ����';
COMMENT ON COLUMN boxOffice.movieCd is '��ȭ�ڵ�';
COMMENT ON COLUMN boxOffice.movieNm is '��ȭ����';
COMMENT ON COLUMN boxOffice.openDt is '������¥';
COMMENT ON COLUMN boxOffice.audiAcc is '����������';


INSERT INTO boxOffice (rank, movieCd, movieNm, openDt, audiAcc)
VALUES (1, '202020', '����', '2018-06-27', '958795');
INSERT INTO boxOffice (rank, movieCd, movieNm, openDt, audiAcc)
VALUES (2, '202020', '����', '2018-06-27', '958795');
INSERT INTO boxOffice (rank, movieCd, movieNm, openDt, audiAcc)
VALUES (3, '202020', '����', '2018-06-27', '958795');
INSERT INTO boxOffice (rank, movieCd, movieNm, openDt, audiAcc)
VALUES (4, '202020', '����', '2018-06-27', '958795');
INSERT INTO boxOffice (rank, movieCd, movieNm, openDt, audiAcc)
VALUES (5, '202020', '����', '2018-06-27', '958795');
INSERT INTO boxOffice (rank, movieCd, movieNm, openDt, audiAcc)
VALUES (6, '202020', '����', '2018-06-27', '958795');
INSERT INTO boxOffice (rank, movieCd, movieNm, openDt, audiAcc)
VALUES (7, '202020', '����', '2018-06-27', '958795');
INSERT INTO boxOffice (rank, movieCd, movieNm, openDt, audiAcc)
VALUES (8, '202020', '����', '2018-06-27', '958795');
INSERT INTO boxOffice (rank, movieCd, movieNm, openDt, audiAcc)
VALUES (9, '202020', '����', '2018-06-27', '958795');
INSERT INTO boxOffice (rank, movieCd, movieNm, openDt, audiAcc)
VALUES (10, '202020', '����', '2018-06-27', '958795');


SELECT * from BOXOFFICE;


SELECT m.thumb, m.genre, m.showTm, m.watchGradeNm, m.director, m.actors, mc.rank, mc.movieCd, mc.movieNm, mc.openDt, mc.audiAcc
FROM movie m, boxOffice mc
WHERE mc.movieCd = m.movieCd;