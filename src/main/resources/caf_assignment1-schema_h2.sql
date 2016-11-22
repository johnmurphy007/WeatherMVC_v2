CREATE TABLE IF NOT EXISTS chObject (
  id varchar(11) NOT NULL,
  title varchar(100) DEFAULT NULL,
  date varchar(11) DEFAULT NULL,
  medium varchar(100) DEFAULT NULL,
  creditline varchar(100) DEFAULT NULL,
  description text,
  gallery_text text
) ENGINE=InnoDB DEFAULT CHARSET=UTF8;

CREATE TABLE IF NOT EXISTS authorities (
  username varchar(254) NOT NULL,
  authority varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=UTF8;


CREATE TABLE IF NOT EXISTS `user` (
`id` int(11) NOT NULL,
  `username` varchar(254) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  `profilename` varchar(64) DEFAULT NULL,
  `address1` varchar(64) DEFAULT NULL,
  `address2` varchar(64) DEFAULT NULL,
  `address3` varchar(64) DEFAULT NULL,
  `email` varchar(254) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `accountstatus` tinyint(1) DEFAULT NULL,
  `pinid` int(11) DEFAULT NULL,
  `datejoined` date DEFAULT NULL,
  `authority` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=UTF8;

CREATE TABLE IF NOT EXISTS `user` (
`id` int(11) NOT NULL,
  `username` varchar(254) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  `profilename` varchar(64) DEFAULT NULL,
  `address1` varchar(64) DEFAULT NULL,
  `address2` varchar(64) DEFAULT NULL,
  `address3` varchar(64) DEFAULT NULL,
  `email` varchar(254) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `accountstatus` tinyint(1) DEFAULT NULL,
  `pinid` int(11) DEFAULT NULL,
  `datejoined` date DEFAULT NULL,
  `authority` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=UTF8;

CREATE TABLE IF NOT EXISTS image (
 image_id varchar(11) NOT NULL,
  image_res varchar(11) NOT NULL,
  chObject_id varchar(11) NOT NULL,
  is_primary varchar(11) DEFAULT NULL,
  height int(11) DEFAULT NULL,
  width int(11) DEFAULT NULL,
  url varchar(256) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=UTF8;

CREATE TABLE IF NOT EXISTS participant (
  person_id varchar(100) NOT NULL,
  person_name varchar(100) DEFAULT NULL,
  person_date varchar(100) DEFAULT NULL,
  person_url varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=UTF8;

CREATE TABLE IF NOT EXISTS participation (
  chObject_id int(11) NOT NULL,
  participant_id int(11) NOT NULL,
  role_id int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=UTF8;

CREATE TABLE IF NOT EXISTS role (
  role_id varchar(100) NOT NULL,
  role_name varchar(100) DEFAULT NULL,
  role_display_name varchar(100) DEFAULT NULL,
  role_url varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=UTF8;


