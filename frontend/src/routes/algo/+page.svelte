<script>
  import { onMount } from 'svelte';
  import {
    fetchAlgorithmTypes,
    fetchAlgorithmsByType,
    postTest
  } from '$lib/api.js';

  let algorithmTypes = [];
  let algorithms = [];
  let selectedType = '';
  let selectedAlgorithm = '';
  let inputData = '';
  let testResult = null;

  onMount(async () => {
    try {
      algorithmTypes = await fetchAlgorithmTypes();
      selectedType = algorithmTypes[0];
      algorithms = await fetchAlgorithmsByType(selectedType);
      selectedAlgorithm = algorithms[0]?.name || '';
    } catch (e) {
      alert('Failed to load algorithms');
    }
  });

  const onTypeChange = async () => {
    algorithms = await fetchAlgorithmsByType(selectedType);
    selectedAlgorithm = algorithms[0]?.name || '';
  };

  const runTest = async () => {
    try {
      testResult = await postTest(selectedAlgorithm, inputData || 'sample data');
    } catch (e) {
      testResult = { error: e.message };
    }
  };

  const clearForm = () => {
    inputData = '';
    testResult = null;
  };

  const benchmark = () => {
    alert('Benchmark feature not implemented yet.');
  };
</script>

<style>
  :global(body) {
    margin: 0;
    font-family: 'Segoe UI', sans-serif;
    background: linear-gradient(135deg, #0f2027, #203a43, #2c5364);
    color: white;
  }

  main {
    max-width: 1200px;
    margin: auto;
    padding: 2rem;
  }

  h1 {
    font-size: 2rem;
    color: #00ffff;
    margin-bottom: 0.3rem;
  }

  p {
    opacity: 0.8;
    margin-bottom: 2rem;
  }

  .grid {
    display: grid;
    grid-template-columns: 1fr 1fr;
    gap: 2rem;
  }

  .panel {
    background: rgba(255, 255, 255, 0.05);
    backdrop-filter: blur(12px);
    border: 1px solid rgba(255, 255, 255, 0.1);
    border-radius: 1rem;
    padding: 2rem;
    box-shadow: 0 10px 25px rgba(0, 0, 0, 0.3);
    transition: transform 0.2s ease;
  }

  .panel:hover {
    transform: scale(1.01);
  }

  label {
    font-weight: 600;
    margin-top: 1rem;
    display: block;
  }

  select, textarea {
    width: 100%;
    margin-top: 0.3rem;
    padding: 0.6rem;
    border-radius: 0.5rem;
    border: none;
    outline: none;
    background: rgba(255, 255, 255, 0.1);
    color: white;
  }

  textarea {
    resize: vertical;
    min-height: 100px;
  }

  .note {
    font-size: 0.85rem;
    color: #ccc;
    margin-top: 0.4rem;
    font-style: italic;
  }

  .buttons {
    margin-top: 1.5rem;
    display: flex;
    gap: 1rem;
    flex-wrap: wrap;
  }

  .buttons button {
    flex: 1;
    padding: 0.75rem 1.25rem;
    font-weight: 600;
    border: none;
    border-radius: 0.5rem;
    cursor: pointer;
    transition: 0.2s;
  }

  .run {
    background: #00bcd4;
  }

  .bench {
    background: #4caf50;
  }

  .clear {
    background: #e74c3c;
  }

  .buttons button:hover {
    filter: brightness(1.1);
  }

  pre {
    background: rgba(0, 0, 0, 0.7);
    padding: 1rem;
    border-radius: 0.5rem;
    overflow-x: auto;
    color: #a0ffaf;
    white-space: pre-wrap;
  }

  .placeholder {
    color: #ccc;
    opacity: 0.7;
  }

  @media (max-width: 900px) {
    .grid {
      grid-template-columns: 1fr;
    }
  }
</style>

<main>
  <h1>Cryptographic Algorithm Analysis Tool</h1>
  <p>Test and benchmark various cryptographic algorithms with different parameters</p>

  <div class="grid">
    <!-- Algorithm Configuration -->
    <div class="panel">
      <h3>🔐 Algorithm Configuration</h3>

      <label>Algorithm Type:</label>
      <select bind:value={selectedType} on:change={onTypeChange}>
        {#each algorithmTypes as type}
          <option>{type}</option>
        {/each}
      </select>

      <label>Select Algorithm:</label>
      <select bind:value={selectedAlgorithm}>
        {#each algorithms as alg}
          <option value={alg.name}>{alg.name}</option>
        {/each}
      </select>

      <div class="note">
        Hash functions produce fixed-size output regardless of input size.
      </div>

      <label>Input Data:</label>
      <textarea bind:value={inputData} placeholder="Enter data or leave blank for sample data" />

      <div class="buttons">
        <button class="run" on:click={runTest}>RUN TEST</button>
        <button class="bench" on:click={benchmark}>BENCHMARK</button>
        <button class="clear" on:click={clearForm}>CLEAR</button>
      </div>
    </div>

    <!-- Test Results -->
    <div class="panel">
      <h3>📊 Test Results</h3>
      {#if testResult}
        <pre>{JSON.stringify(testResult, null, 2)}</pre>
      {:else}
        <div class="placeholder">
          Results will appear here after running a test.
          <br />
          Select an algorithm and click "Run Test" to begin.
        </div>
      {/if}
    </div>
  </div>
</main>
