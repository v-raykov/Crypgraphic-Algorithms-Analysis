<script>
    import { Router, Route, Link } from 'svelte-routing';
    import login from '../login/+page.svelte';
    import register from '../register/+page.svelte';
  </script>
  
  <Router>
    <nav>
      <Link to="/login">login</Link>
      <Link to="/register">register</Link>
    </nav>
  
    <Route path="/login" component={login} />
    <Route path="/register" component={register} />
  </Router>

  