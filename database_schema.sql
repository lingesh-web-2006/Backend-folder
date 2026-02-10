 CREATE TABLE users (
    id BIGSERIAL PRIMARY KEY,
    email VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    name VARCHAR(255) NOT NULL,
    role VARCHAR(50) NOT NULL DEFAULT 'USER',
    enabled BOOLEAN NOT NULL DEFAULT true,
    created_at BIGINT,
    updated_at BIGINT
);
CREATE TABLE domains (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    icon VARCHAR(10),
    difficulty VARCHAR(50),
    skill_count INTEGER
);
CREATE TABLE domain_top_skills (
    domain_id BIGINT NOT NULL,
    skill VARCHAR(255),
    FOREIGN KEY (domain_id) REFERENCES domains(id)
);

-- Create Skills Table
CREATE TABLE skills (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    level VARCHAR(50),
    importance INTEGER,
    domain_id BIGINT NOT NULL,
    user_id BIGINT,
    FOREIGN KEY (domain_id) REFERENCES domains(id),
    FOREIGN KEY (user_id) REFERENCES users(id)
);

-- Create Projects Table
CREATE TABLE projects (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    icon VARCHAR(10),
    difficulty VARCHAR(50),
    estimated_hours INTEGER,
    starter_html TEXT,
    starter_css TEXT,
    starter_js TEXT,
    domain_id BIGINT NOT NULL,
    FOREIGN KEY (domain_id) REFERENCES domains(id)
);

-- Create Project Resources Table
CREATE TABLE project_resources (
    project_id BIGINT NOT NULL,
    resources VARCHAR(255),
    FOREIGN KEY (project_id) REFERENCES projects(id)
);

-- Create Workflow Steps Table
CREATE TABLE workflow_steps (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    step_order INTEGER,
    completed BOOLEAN DEFAULT false,
    project_id BIGINT NOT NULL,
    FOREIGN KEY (project_id) REFERENCES projects(id)
);

-- Create Quizzes Table
CREATE TABLE quizzes (
    id BIGSERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    description TEXT,
    question_count INTEGER,
    estimated_time INTEGER,
    domain_id BIGINT NOT NULL,
    FOREIGN KEY (domain_id) REFERENCES domains(id)
);

-- Create Questions Table
CREATE TABLE questions (
    id BIGSERIAL PRIMARY KEY,
    question TEXT NOT NULL,
    correct_answer VARCHAR(255) NOT NULL,
    hint TEXT,
    quiz_id BIGINT NOT NULL,
    FOREIGN KEY (quiz_id) REFERENCES quizzes(id)
);

-- Create Question Options Table
CREATE TABLE question_options (
    question_id BIGINT NOT NULL,
    option_text VARCHAR(255),
    FOREIGN KEY (question_id) REFERENCES questions(id)
);

-- Create User Progress Table
CREATE TABLE user_progress (
    id BIGSERIAL PRIMARY KEY,
    user_id BIGINT NOT NULL UNIQUE,
    total_skills_learned INTEGER DEFAULT 0,
    total_projects_completed INTEGER DEFAULT 0,
    total_quizzes_completed INTEGER DEFAULT 0,
    current_streak INTEGER DEFAULT 0,
    total_points INTEGER DEFAULT 0,
    last_activity_date BIGINT,
    FOREIGN KEY (user_id) REFERENCES users(id)
);

-- Create Achievements Table
CREATE TABLE achievements (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    icon VARCHAR(10),
    requirement TEXT,
    user_id BIGINT,
    unlocked BOOLEAN DEFAULT false,
    unlocked_date VARCHAR(255),
    FOREIGN KEY (user_id) REFERENCES users(id)
);

-- Insert Sample Data

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
-- Create Indexes for Performance
CREATE INDEX idx_skills_domain_id ON skills(domain_id);
CREATE INDEX idx_projects_domain_id ON projects(domain_id);
CREATE INDEX idx_workflow_steps_project_id ON workflow_steps(project_id);
CREATE INDEX idx_quizzes_domain_id ON quizzes(domain_id);
CREATE INDEX idx_questions_quiz_id ON questions(quiz_id);
CREATE INDEX idx_question_options_question_id ON question_options(question_id);
CREATE INDEX idx_achievements_user_id ON achievements(user_id);
CREATE INDEX idx_user_progress_user_id ON user_progress(user_id);
