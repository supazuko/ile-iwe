set foreign_key_checks = 0;
-- truncate table course;
-- truncate table student;
-- truncate table learning party;

insert into course(id, title, is_published,description, language, duration)
values(1, 'Physics', true,'sciences', 'English', '1hr'),
      (2, 'Literature', true,'art', 'English','1hr'),
      (3, 'Accounting', true, 'commercial', 'English','1hr');

insert into student(id, first_name, last_name, gender)
values(4, 'Iseoluwa', 'Ayeni', 'FEMALE'),
      (5, 'Austin', 'Ekweozor', 'MALE'),
      (6, 'Bolu', 'Fadoju', 'FEMALE');

insert into learning_party(id, email, password,enabled)
values(7, 'iseAyeni@gmail.com', 'ay123', true),
      (8, 'ekwe@gmail.com', 'eboy123', true),
      (9, 'bm@gmail.com', 'fad123', false );

insert into instructor(id, first_name, last_name, gender, specialization, bio)
values(10, 'Ifeanyi', 'MC', 'MALE', 'all sciences', 'lorem'),
      (11, 'Yinka', 'Lawal', 'FEMALE', 'writing', 'lorem'),
      (12, 'Dogo', 'William', 'MALE', 'book keping', 'lorem');

set foreign_key_checks = 1;