export function login (username, password){
    return fetch('http://localhost:8080/login', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ username, password })
      })
}

export function register ( username, email, password){
    return fetch('http://localhost:8080/register', {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify({ username, email, password })
    })
}
export async function fetchAllAlgorithms(){
    const res = await fetch('http://localhost:8080/algorithm', {
      method: 'GET',
      headers: { 'Content-Type': 'application/json' }
    })
    return await res.json()
}

export async function fetchAlgorithmByName(name){
  const res = await fetch('http://localhost:8080/algorithm/name/' + name, {
    method: 'GET',
    headers: { 'Content-Type': 'application/json' }
  })
  return await res.json()
}

export async function postTest(body){
  const res = await fetch('http://localhost:8080/test', {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify(body)
  })
  return await res.json()

}