INSERT INTO `role`
VALUES (0, 'User', 'Can buy tickets'), (1, 'Admin', 'Can everything');

INSERT INTO `user`
VALUES (1, 'Vasilii', 'vasya@gmail.com', '202cb962ac59075b964b07152d234b70', 0),
       (2, 'Lizo4ka', 'liza@kiev.ua', '24f6e3dc1bbc5a5dfb1e5c6481b94eb7', 0),
       (3, 'Julia', 'j.savch@kiev.ua', 'love', 0),
       (4, 'Irka', 'fairy@fairy.com', 'puf', 1);

INSERT INTO `exhibition`
VALUES (1, 'Music concert', '2020-12-12', '2020-12-17', 'Music', 'Listen to perfect melody',
        'Listen to perfect melody', 700, '/img/100.png'),
       (2, 'Pinchuk Art Center', '2020-06-06', '2020-07-07', 'Art', 'Modern art',
        'Pictures, sculptures, movies and more', 200, '/img/101.png'),
       (3, 'Classical wheel', '2020-02-26', '2020-07-27', 'Art', 'Classical art',
        'Pictures, sculptures from classic artists and more', 33.33, '/img/101.png'),
       (4, 'Win a lotto', '2020-06-06', '2020-06-27', 'Casino', 'Win money', 'Poker, lotto etc.',
        666.77, '/img/101.png');

INSERT INTO `exposition`
VALUES (1, 'Scream', 'Legendary art', '/img/1.jpg', 1),
       (2, 'Apolon', 'Masculine sculpture', '/img/12.jpg', 1),
       (3, 'Jazz', 'Relaxing chill music', '/img/2.jpg', 2);

INSERT INTO `ticket`
VALUES (1, 3, 3, 0), (2, 3, 3, 1), (3, 4, 4, 1);

