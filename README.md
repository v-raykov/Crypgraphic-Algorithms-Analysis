# Endpoints
## Authentication
- /login (POST)
  - `{username, password}`
- /register (POST)
  - `{username, email, password`
## Algorithms
- /algorithm (GET)

## Test
- /test (GET)
- /test/{id} (GET)
- /test (POST)
  - has two required and many optional fields.
  - required fields: `{algorithm, plaintext}`
  - the optional fields depend on the algorithm type
  - encryption optional fields: `encryptionKey, keySize, iv`

## User
- /user (GET)
  - `{username, email, role}`
- /user/change-password (PUT)
  - `{password, newPassword}`
- /user/change-email (PUT)
  - `{password, newEmail}`
- /user/change-username (PUT)
  - `{password, newUsername}`
## Admin
All request to admin endpoint can also be sent to owner, if the user is the owner
- /admin (GET)
  - Should be the dashboard. Left for implementation
- /admin/users (GET)
  -  [{}]
- /admin/users/`{id}` (DELETE)
  - deletes the user with that id. Only owner can delete other admin users. If admin tries to delete admin ForbiddenUserDeletionException is thrown
- /owner/users/`{id}`/adminStatus/`{isAdmin}`
  - changes if user is admin to the value of `isAdmin` (boolean)
