# Endpoints
## Authentication
- /login (POST)
  - `{username, password}`
- /register (POST)
  - `{username, email, password`
## Algorithms
- /algorithm (GET)
- /algorithm/name/{name} (GET)
- /algorithm/type/{type} (GET) 
  - the type value should be the type field of the algorithm but in kebab case (e.g. "Key Derivation" -> key-derivation)

## Test
- /test (GET)
- /test/{id} (GET)
- /test (POST)
  - has two required fields.
  - required fields: `{algorithm, plaintext}`
  - optional field `parameters`. The user can influence the parameters of the algorithm. If not provided, default values are set.
  - `parameters` is an object and the fields inside it depend on the type of algorithm being tested.
      - for block cipher encryption: `encryptionKey, keySize, iv`
      - for stream cipher encryption: `encryptionKey, keySize`

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
- /owner/users/`{id}`/adminStatus/`{isAdmin}` (PUT)
  - changes if user is admin to the value of `isAdmin` (boolean)
