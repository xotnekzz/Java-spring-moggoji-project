/**********************************/
/* Table Name: �亯_���ƿ� */
/**********************************/
CREATE TABLE rcrlike(
    rcrlikeno                           NUMBER     NOT NULL,
    rcrno                             NUMBER     NOT NULL,
    memberno                          NUMBER     NOT NULL,
    PRIMARY KEY (rcrlikeno),
    FOREIGN KEY (rcrno) REFERENCES rcomment_reply(rcrno) ON DELETE CASCADE,
    FOREIGN KEY (memberno) REFERENCES member(memberno) ON DELETE CASCADE
);

DROP TABLE rcrlike;

