<script>
  import { onMount } from 'svelte';
  import {
    fetchAlgorithmTypes,
    postTest
  } from '$lib/api.js';

  let algorithmTypes = [];
  let allAlgorithms = [];
  let algorithms = [];
  let selectedType = '';
  let selectedAlgorithm = '';
  let inputData = '';
  let testResult = null;
  let parameterValues = {};

  onMount(async () => {
    try {
      allAlgorithms = await fetchAlgorithmTypes();
      algorithmTypes = [...new Set(allAlgorithms.map(a => a.type))];
      selectedType = algorithmTypes[0];
      algorithms = allAlgorithms.filter(a => a.type === selectedType);
      selectedAlgorithm = algorithms[0]?.name || '';
      updateParameters();
    } catch (e) {
      alert('Failed to load algorithms');
    }
  });

  const onTypeChange = () => {
    algorithms = allAlgorithms.filter(a => a.type === selectedType);
    selectedAlgorithm = algorithms[0]?.name || '';
    updateParameters();
  };

  const onAlgorithmChange = () => {
    updateParameters();
  };

  const updateParameters = () => {
    const algorithmObj = allAlgorithms.find(a => a.name === selectedAlgorithm);
    parameterValues = {};

    if (!algorithmObj?.parameters) return;

    algorithmObj.parameters.forEach(param => {
      switch (param) {
        case 'keySize':
          parameterValues[param] = '128';
          break;
        case 'salt':
          parameterValues[param] = crypto.getRandomValues(new Uint8Array(8)).join('');
          break;
        case 'iv':
          parameterValues[param] = crypto.getRandomValues(new Uint8Array(16)).join('');
          break;
        case 'ivSize':
          parameterValues[param] = '16';
          break;
        case 'iterations':
          parameterValues[param] = '1000';
          break;
        case 'costFactor':
          parameterValues[param] = '16384';
          break;
        case 'blockSize':
          parameterValues[param] = '8';
          break;
        case 'parallelization':
          parameterValues[param] = '1';
          break;
        case 'encryptionKey':
          parameterValues[param] = 'mySecretKey';
          break;
        case 'publicKey':
        case 'privateKey':
          parameterValues[param] = '';
          break;
        default:
          parameterValues[param] = '';
      }
    });
  };

  const runTest = async () => {
    for (const [key, value] of Object.entries(parameterValues)) {
      if (value.trim() === '') {
        alert(`Please provide a value for parameter: ${key}`);
        return;
      }
    }

    try {
      testResult = await postTest(selectedAlgorithm, inputData || 'sample data', parameterValues);
    } catch (e) {
      testResult = { error: e.message };
    }
  };

  const clearForm = () => {
    inputData = '';
    testResult = null;
    updateParameters();
  };

  const benchmark = () => {
    alert('Benchmark feature not implemented yet.');
  };
</script>

<main>
  <h1>Cryptographic Algorithm Tester Tool</h1>
  <p>Test and benchmark various cryptographic algorithms with different parameters</p>

  <div class="grid">
    <!-- Configuration Panel -->
    <div class="panel">
      <h3>🔐 Algorithm Configuration</h3>

      <label>Algorithm Type:</label>
      <select bind:value={selectedType} on:change={onTypeChange}>
        {#each algorithmTypes as type}
          <option value={type}>{type}</option>
        {/each}
      </select>

      <label>Select Algorithm:</label>
      <select bind:value={selectedAlgorithm} on:change={onAlgorithmChange}>
        {#each algorithms as alg}
          <option value={alg.name}>{alg.name}</option>
        {/each}
      </select>

      {#if Object.keys(parameterValues).length > 0}
        <label>Algorithm Parameters:</label>
        {#each Object.keys(parameterValues) as param}
          <div>
            <label>{param}:</label>
            <input type="text" bind:value={parameterValues[param]} />
          </div>
        {/each}
      {/if}

      <label>Input Data:</label>
      <textarea bind:value={inputData} placeholder="Enter data or leave blank for sample data" />

      <div class="buttons">
        <button class="run" on:click={runTest}>RUN TEST</button>
        <button class="bench" on:click={benchmark}>BENCHMARK</button>
        <button class="clear" on:click={clearForm}>CLEAR</button>
      </div>
    </div>

    <!-- Results Panel -->
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

  select, textarea, input {
    width: 100%;
    margin-top: 0.3rem;
    padding: 0.6rem;
    border-radius: 0.5rem;
    border: none;
    outline: none;
    background: rgba(255, 255, 255, 0.1);
    color: white;
  }

  select option {
    background: #203a43;
    color: white;
  }

  select:focus {
    background: rgba(255, 255, 255, 0.15);
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
