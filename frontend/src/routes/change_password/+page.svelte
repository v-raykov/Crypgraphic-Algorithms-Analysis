<script>
    import { navigate } from 'svelte-routing';
    import { register } from '$lib/api.js'

    let currentPassword = '';
    let newPassword = '';
    let confirmPassword = '';
    let message = '';
  
    const handleChangePassword = async () => {
      if (!currentPassword || !newPassword || !confirmPassword) {
        message = 'All fields are required!';
        return;
      }
  
      if (newPassword !== confirmPassword) {
        message = "New passwords don't match!";
        return;
      }
  
      const token = localStorage.getItem('token');
      if (!token) {
        navigate('/login');
        return;
      }
  
      try {
        const response = await fetch('http://localhost:8080/api/user/change-password', {
          method: 'POST',
          headers: {
            'Content-Type': 'application/json',
            'Authorization': `Bearer ${token}`, // Send JWT token for authentication
          },
          body: JSON.stringify({ currentPassword, newPassword }),
        });
  
        const data = await response.json();
        message = data.message;
  
        if (response.ok) {
          alert('Password changed successfully! Please log in again.');
          localStorage.removeItem('token'); // Log out user
          navigate('/login');
        }
      } catch (err) {
        console.error(err);
        message = 'Error changing password.';
      }
    };
  </script>
  
  <main>
    <div class="form-container">
      <h1>Change Password</h1>
      <form on:submit|preventDefault={handleChangePassword}>
        <div class="input-group">
          <label for="currentPassword">Current Password</label>
          <input id="currentPassword" type="password" bind:value={currentPassword} required />
        </div>
  
        <div class="input-group">
          <label for="newPassword">New Password</label>
          <input id="newPassword" type="password" bind:value={newPassword} required />
        </div>
  
        <div class="input-group">
          <label for="confirmPassword">Confirm New Password</label>
          <input id="confirmPassword" type="password" bind:value={confirmPassword} required />
        </div>
  
        {#if message}
          <p class="error">{message}</p>
        {/if}
  
        <button type="submit">Change Password</button>
      </form>
    </div>
  </main>
  