SELECT
  u.name,
  u.email
FROM users AS u
  JOIN friends AS f ON u.id = f.friend_two
WHERE f.friend_one = '2' AND f.status = 'fri'
ORDER BY (u.name);