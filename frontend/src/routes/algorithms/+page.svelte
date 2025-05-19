<script>
  import { onMount } from 'svelte';
  import {
    login,
    register,
    fetchAlgorithmTypes,
    fetchAlgorithmsByType,
    fetchAlgorithmByName,
    fetchTests,
    fetchTestById,
    postTest,
    fetchUser,
    changePassword,
    changeEmail,
    changeUsername,
    fetchAdminDashboard,
    fetchAdminUsers,
    deleteUser,
    setUserAdminStatus
  } from '$lib/api.js';
 let algorithmTypes = [];
  let selectedType = '';
  let algorithms = [];
  let selectedAlgorithm = '';
  let errorMessage = '';

  onMount(async () => {
    errorMessage = '';
    try {
      algorithmTypes = await fetchAlgorithmTypes();
    } catch (err) {
      errorMessage = err.message || 'Failed to load algorithm types';
    }
  });

  async function onSelectType() {
    selectedAlgorithm = '';
    algorithms = [];
    errorMessage = '';
    if (!selectedType) return;

    try {
      algorithms = await fetchAlgorithmsByType(selectedType);
    } catch (err) {
      errorMessage = err.message || 'Failed to load algorithms';
    }
  }
</script>

<main>
  <div class="card">
    <h1>Select & Analyze Algorithm</h1>

    <div class="form-group">
      <label for="type">Algorithm Type</label>
      <select id="type" on:change={onSelectType} bind:value={selectedType}>
        <option value="" disabled selected>Select Algorithm Type</option>
        {#each algorithmTypes as type}
          <option value={type.id}>{type.name}</option>
        {/each}
      </select>
    </div>

    {#if algorithms.length > 0}
      <div class="form-group">
        <label for="algorithm">Algorithm</label>
        <select id="algorithm" bind:value={selectedAlgorithm}>
          <option value="" disabled selected>Select Algorithm</option>
          {#each algorithms as algo}
            <option value={algo.id || algo}>{algo.name || algo}</option>
          {/each}
        </select>
      </div>
    {/if}

    <button disabled={!selectedAlgorithm} on:click={() => alert(`Selected Algorithm: ${selectedAlgorithm}`)}>
      🔍 Analyze
    </button>

    {#if errorMessage}
      <div class="error" aria-live="assertive">{errorMessage}</div>
    {/if}
  </div>
</main>

<style>
  @import url('https://fonts.googleapis.com/css2?family=Inter:wght@400;600;700&display=swap');

  main {
    font-family: 'Inter', sans-serif;
    background: linear-gradient(145deg, #f0f4f8, #e2e8f0);
    min-height: 100vh;
    display: flex;
    justify-content: center;
    align-items: center;
    padding: 2rem;
  }

  .card {
    background: white;
    padding: 2rem;
    border-radius: 16px;
    box-shadow: 0 10px 25px rgba(0, 0, 0, 0.1);
    width: 100%;
    max-width: 420px;
    display: flex;
    flex-direction: column;
    gap: 1rem;
    animation: fadeIn 0.6s ease-out;
  }

  h1 {
    font-size: 1.75rem;
    color: #1c9dea;
    text-align: center;
    margin-bottom: 1rem;
  }

  .form-group {
    display: flex;
    flex-direction: column;
    gap: 0.5rem;
  }

  label {
    font-weight: 600;
    color: #333;
  }

  select {
    padding: 0.75rem;
    border: 1px solid #ccc;
    border-radius: 10px;
    font-size: 1rem;
    background-color: #f9fafb;
    transition: border-color 0.3s;
  }

  select:focus {
    border-color: #1c9dea;
    outline: none;
  }

  button {
    background-color: #1c9dea;
    color: white;
    border: none;
    border-radius: 10px;
    padding: 0.85rem;
    font-weight: 600;
    font-size: 1rem;
    cursor: pointer;
    transition: all 0.3s ease;
    margin-top: 1rem;
  }

  button:hover {
    background-color: #1590e2;
    transform: translateY(-2px) scale(1.02);
    box-shadow: 0 5px 15px rgba(28, 157, 234, 0.3);
  }

  button:disabled {
    background-color: #c4cbd4;
    cursor: not-allowed;
    transform: none;
    box-shadow: none;
  }

  .error {
    color: #e63946;
    font-weight: 600;
    background-color: #ffe5e5;
    padding: 0.75rem;
    border-radius: 8px;
    margin-top: 0.5rem;
    text-align: center;
    animation: fadeIn 0.4s ease;
  }

  @keyframes fadeIn {
    from { opacity: 0; transform: translateY(10px); }
    to { opacity: 1; transform: translateY(0); }
  }

  @media (max-width: 480px) {
    .card {
      padding: 1.5rem;
    }

    h1 {
      font-size: 1.5rem;
    }

    select, button {
      font-size: 0.95rem;
    }
  }
</style>