set foreign_key_checks = 0;
-- truncate table course;
-- truncate table student;
truncate table learning_party;
truncate table authority;
truncate table instructor;

-- insert into course(id, title, is_published,description, language, duration)
-- values(1, 'Physics', true,'sciences', 'English', '1hr'),
--       (2, 'Literature', true,'art', 'English','1hr'),
--       (3, 'Accounting', true, 'commercial', 'English','1hr');
--
-- insert into student(id, first_name, last_name, gender)
-- values(4, 'Iseoluwa', 'Ayeni', 'FEMALE'),
--       (5, 'Austin', 'Ekweozor', 'MALE'),
--       (6, 'Bolu', 'Fadoju', 'FEMALE');
--
insert into learning_party(`id`, `email`, `password`, `enabled`)
values(123, 'ise@email.com', 'ise123', false),
      (124, 'yiu@email.com', 'yiu123', false),
      (125, 'toni@email.com', 'toni123', false),
      (126, 'dan@email.com', 'dan123', false),
      (127, 'easy@email.com', 'easy123', false),
      (128, 'mike@email.com', 'mike123', false);
--
-- insert into instructor(id, first_name, last_name, gender, specialization, bio)
-- values(10, 'Ifeanyi', 'MC', 'MALE', 'all sciences', 'lorem'),
--       (11, 'Yinka', 'Lawal', 'FEMALE', 'writing', 'lorem'),
--       (12, 'Dogo', 'William', 'MALE', 'book keping', 'lorem');

set foreign_key_checks = 1;