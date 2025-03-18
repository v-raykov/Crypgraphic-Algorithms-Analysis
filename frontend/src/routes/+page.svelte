<script>
    import { Router, Route, Link } from 'svelte-routing';
    import login from './login/+page.svelte';
    import user from './user/+page.svelte';
  </script>
  
  <Router>
    <nav>
      <Link to="/user">user</Link>
      <Link to="/login">login</Link>
    </nav>
  
    <Route path="/user" component={user} />
    <Route path="/login" component={login} />
  </Router>