CREATE TABLE `employee` (
  `id` bigint NOT NULL,
  `age` int NOT NULL,
  `company` varchar(255) DEFAULT NULL,
  `contact_number` bigint NOT NULL,
  `department` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `gender` varchar(255) DEFAULT NULL,
  `location` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `pincode` int NOT NULL,
  `position` varchar(255) DEFAULT NULL,
  `salary` bigint NOT NULL,
  `street_address` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
);