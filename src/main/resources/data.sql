-- INSERT Domains
INSERT INTO domains (name, description, icon, difficulty, skill_count)
VALUES 
('Full-Stack Development', 'Learn to build complete web applications', '🌐', 'Intermediate', 12),
('AI/Machine Learning', 'Master AI and ML algorithms', '🤖', 'Advanced', 15),
('Data Science', 'Analyze and visualize data', '📊', 'Intermediate', 10),
('Mobile Development', 'Build iOS and Android apps', '📱', 'Intermediate', 11),
('Cloud Computing', 'Deploy apps to cloud platforms', '☁️', 'Advanced', 9),
('Cybersecurity', 'Secure applications and systems', '🔒', 'Advanced', 13);

-- INSERT Top Skills for Full-Stack
INSERT INTO domain_top_skills (domain_id, skill) VALUES
((SELECT id FROM domains WHERE name = 'Full-Stack Development'), 'JavaScript'),
((SELECT id FROM domains WHERE name = 'Full-Stack Development'), 'React'),
((SELECT id FROM domains WHERE name = 'Full-Stack Development'), 'Node.js');

-- INSERT Skills for Full-Stack
INSERT INTO skills (name, description, level, importance, domain_id)
VALUES 
('HTML', 'Markup language for web pages', 'Beginner', 5, (SELECT id FROM domains WHERE name = 'Full-Stack Development')),
('CSS', 'Styling web pages', 'Beginner', 5, (SELECT id FROM domains WHERE name = 'Full-Stack Development')),
('JavaScript', 'Programming language for web', 'Intermediate', 5, (SELECT id FROM domains WHERE name = 'Full-Stack Development')),
('React', 'Frontend library', 'Intermediate', 4, (SELECT id FROM domains WHERE name = 'Full-Stack Development')),
('Node.js', 'Backend runtime', 'Intermediate', 4, (SELECT id FROM domains WHERE name = 'Full-Stack Development')),
('Express.js', 'Backend framework', 'Intermediate', 4, (SELECT id FROM domains WHERE name = 'Full-Stack Development'));

-- Sample Projects
INSERT INTO projects (name, description, icon, difficulty, estimated_hours, domain_id, starter_html, starter_css, starter_js)
VALUES 
('Build a Todo App', 'Create a simple todo list application with add, edit, delete', '✅', 'Beginner', 5,
(SELECT id FROM domains WHERE name = 'Full-Stack Development'),
'<div id="root"><h1>Todo List</h1><input type="text" id="input"><button>Add</button><ul id="list"></ul></div>',
'body { font-family: Arial; background: #f0f0f0; } #root { max-width: 500px; margin: 50px auto; background: white; padding: 20px; border-radius: 8px; }',
'const input = document.getElementById("input"); const list = document.getElementById("list"); function addTodo() { const text = input.value; if (text) { const li = document.createElement("li"); li.textContent = text; list.appendChild(li); input.value = ""; } } document.querySelector("button").addEventListener("click", addTodo);');

-- Sample Quizzes
INSERT INTO quizzes (title, description, question_count, estimated_time, domain_id)
VALUES 
('JavaScript Basics', 'Test your JavaScript fundamentals', 5, 10, (SELECT id FROM domains WHERE name = 'Full-Stack Development')),
('React Fundamentals', 'Test your React knowledge', 5, 15, (SELECT id FROM domains WHERE name = 'Full-Stack Development'));

-- Sample Questions
INSERT INTO questions (question, correct_answer, hint, quiz_id)
VALUES 
('What is the DOM?', 'Document Object Model', 'It is related to the browser representation of HTML', (SELECT id FROM quizzes WHERE title = 'JavaScript Basics')),
('How do you declare a variable in JavaScript?', 'const/let/var', 'Use this, let, or const keyword', (SELECT id FROM quizzes WHERE title = 'JavaScript Basics')),
('What is React?', 'A JavaScript library for building user interfaces', 'Made by Facebook, used for frontend', (SELECT id FROM quizzes WHERE title = 'React Fundamentals')),
('What are props in React?', 'Arguments passed to React components', 'Similar to function parameters', (SELECT id FROM quizzes WHERE title = 'React Fundamentals')),
('What is state in React?', 'Data that can change during component lifetime', 'Opposite of props, it is internal', (SELECT id FROM quizzes WHERE title = 'React Fundamentals'));

-- Sample Question Options
INSERT INTO question_options (question_id, option_text) VALUES
((SELECT id FROM questions WHERE question LIKE 'What is the DOM%'), 'Document Object Model'),
((SELECT id FROM questions WHERE question LIKE 'What is the DOM%'), 'Data Object Manager'),
((SELECT id FROM questions WHERE question LIKE 'What is the DOM%'), 'Debug Object Model'),
((SELECT id FROM questions WHERE question LIKE 'How do you declare%'), 'const/let/var'),
((SELECT id FROM questions WHERE question LIKE 'How do you declare%'), 'function'),
((SELECT id FROM questions WHERE question LIKE 'How do you declare%'), 'class');

-- Sample Workflow Steps
INSERT INTO workflow_steps (name, description, step_order, completed, project_id)
VALUES
('Frontend Setup', 'Create HTML structure for the todo app', 1, false, (SELECT id FROM projects WHERE name = 'Build a Todo App')),
('Styling', 'Add CSS to make it look beautiful', 2, false, (SELECT id FROM projects WHERE name = 'Build a Todo App')),
('JavaScript Logic', 'Add functionality to add, edit, delete todos', 3, false, (SELECT id FROM projects WHERE name = 'Build a Todo App')),
('Testing', 'Test all features work correctly', 4, false, (SELECT id FROM projects WHERE name = 'Build a Todo App')),
('Deployment', 'Deploy to the web', 5, false, (SELECT id FROM projects WHERE name = 'Build a Todo App'));
