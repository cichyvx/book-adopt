--
-- table: book
--

INSERT INTO `book` (`id`, `name`, `author`, `description`) VALUES
(1, 'Harry Potter and the Chamber of Secrets', 'J.K. Rowling', 'vol 2'),
(2, 'Harry Potter and the Prisoner of Azkaban', 'J.K. Rowling', 'vol 3'),
(3, 'Harry Potter and the Goblet of Fire', 'J.K. Rowling', 'vol 4'),
(3, 'Harry Potter and the Order of the Phoenix', 'J.K. Rowling', 'vol 5'),
(4, 'Harry Potter and the Half-Blood Prince', 'J.K. Rowling', 'vol 6'),
(5, 'Harry Potter and the Deathly Hallows', 'J.K. Rowling', 'vol 7'),
(6, 'You', 'Caroline Kepnes', 'You is a thriller novel by Caroline Kepnes'),
(7, 'Ferdydurke', 'Witold Gombrowicz', 'Ferdydurke is a novel by the Polish writer Witold Gombrowicz');

------------------------------------------------------------------------------------

--
-- table: user
--

-- password is same as username

INSERT INTO `user` (`id`, `username`, `password`, `role`) VALUES
(1, 'user', '$2a$10$oTEQ0J9HfMS6LUQYvNugye3Z.SINmoFiOh2A/1x1vIOtMMalSLQpi', 'USER'),
(2, 'admin', '$2a$10$2aJ8hit8OxQaJO0b8nC5UOGe2SOHcwXiEdfjDlLOzWDR5UaLV0UrS', 'ADMIN');