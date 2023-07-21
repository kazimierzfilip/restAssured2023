-- File with example SQL queries

-- Select created post

SELECT
    id,
    user_id,
    title,
    body
FROM posts
WHERE post_id = ?


-- Count posts by active users

SELECT
    u.id, count(*)
FROM posts p
    JOIN users u ON u.id = p.user_id
WHERE u.active = 1
GROUP BY user_id