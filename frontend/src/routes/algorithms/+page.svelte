<script>
  import { onMount } from 'svelte';
  import { fetchAlgorithmTypes, postTest } from '$lib/api.js';
  import { testHistory } from '$lib/stores.js';

  let algorithmTypes = [];
  let allAlgorithms = [];
  let algorithms = [];
  let selectedType = '';
  let selectedAlgorithm = '';
  let data = '';
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
      parameterValues[param] = '';
    });
  };

  const runTest = async () => {
    const filteredParams = {};
    for (const [key, value] of Object.entries(parameterValues)) {
      if (value.trim() !== '') {
        filteredParams[key] = value;
      }
    }

    try {
      const result = await postTest(selectedAlgorithm, data || 'sample data', filteredParams);
      testResult = result;
      testHistory.update(history => [result, ...history]); // update shared store
    } catch (e) {
      testResult = { error: e.message };
    }
  };

  const clearForm = () => {
    data = '';
    testResult = null;
    updateParameters();
  };
</script>

<main>
  <h1>Cryptographic Algorithms Analysis Tool</h1>
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
      <textarea bind:value={data} placeholder="Enter data or leave blank for sample data" />

      <div class="buttons">
        <button class="run" on:click={runTest}>RUN TEST</button>
        <button class="clear" on:click={clearForm}>CLEAR</button>
        <a href="/test-history" class="history">TEST HISTORY</a>
      </div>
    </div>

    <!-- Results Panel -->
    <div class="panel">
      <h3>📊 Test Result</h3>

      {#if testResult}
        {#if testResult.error}
          <p class="text-red-400 font-semibold">{testResult.error}</p>
        {:else}
          <div class="result-box">
            <h4>Test ID</h4>
            <p>{testResult.id}</p>

            <h4>Timestamp</h4>
            <p>{testResult.timestamp}</p>

            <h4>Test Details</h4>
            <ul>
              <li><strong>Algorithm:</strong> {testResult.test?.algorithm}</li>
              {#each Object.entries(testResult.test?.parameters || {}) as [key, value]}
                <li><strong>{key}:</strong> {value}</li>
              {/each}
            </ul>

            {#if testResult.cipherText}
              <h4>Cipher Text</h4>
              <p class="mono">{testResult.cipherText}</p>
            {/if}

            {#if testResult.decipherText}
              <h4>Decryption Result</h4>
              <p class="mono">{testResult.decipherText}</p>
            {/if}

            <h4>Performance</h4>
            <ul>
              <li><strong>Time:</strong> {testResult.performance?.cipherTime}</li>
              <li><strong>Memory:</strong> {testResult.performance?.cipherMemory}</li>
            </ul>

            <h4>Security</h4>
            <ul>
              <li><strong>Entropy:</strong> {testResult.security?.entropy.toFixed(4)}</li>
              <li><strong>Frequency Score:</strong> {testResult.security?.frequencyScore.toFixed(4)}</li>
            </ul>
          </div>
        {/if}
      {:else}
        <div class="placeholder">
          Results will appear here after running a test.<br />
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
  }

  textarea {
    resize: vertical;
    min-height: 100px;
  }

  .buttons {
    margin-top: 1.5rem;
    display: flex;
    gap: 1rem;
    flex-wrap: wrap;
  }

  .buttons button, .buttons a.history {
    flex: 1;
    padding: 0.75rem 1.25rem;
    font-weight: 600;
    border: none;
    border-radius: 0.5rem;
    cursor: pointer;
    transition: 0.2s;
    text-align: center;
    text-decoration: none;
  }

  .run {
    background: #00bcd4;
    color: white;
  }

  .clear {
    background: #e74c3c;
    color: white;
  }

  .history {
    background: #9b59b6;
    color: white;
  }

  .buttons button:hover, .buttons a.history:hover {
    filter: brightness(1.1);
  }

  .placeholder {
    color: #ccc;
    opacity: 0.7;
    font-size: 0.9rem;
  }

  .result-box h4 {
    font-size: 1rem;
    margin-top: 1.2rem;
    margin-bottom: 0.3rem;
    color: #00ffff;
  }

  .result-box p, .result-box li {
    font-size: 0.95rem;
    color: #e0f7fa;
    margin-left: 1rem;
  }

  .result-box ul {
    margin-left: 1rem;
    list-style: disc;
  }

  .mono {
    font-family: 'Courier New', monospace;
    background: rgba(0, 0, 0, 0.6);
    padding: 0.4rem 0.8rem;
    border-radius: 0.3rem;
    overflow-wrap: break-word;
  }

  .text-red-400 {
    color: #f87171;
  }

  @media (max-width: 900px) {
    .grid {
      grid-template-columns: 1fr;
    }
  }
</style>
