<script>
  import { onMount } from 'svelte';
  import { testHistory } from '$lib/stores.js';
  import Chart from 'chart.js/auto';
  import { writable } from 'svelte/store';

  let chart;
  let chartRef;
  let selectedMetric = 'cipherTime';

  const metricOptions = [
    { value: 'cipherTime', label: 'Time (ms)' },
    { value: 'cipherMemory', label: 'Memory (KB)' },
    { value: 'entropy', label: 'Entropy' },
    { value: 'frequencyScore', label: 'Frequency Score' }
  ];

  function getReadableName(result) {
    return `${result.test.algorithm} @ ${new Date(result.timestamp).toLocaleTimeString()}`;
  }

  function updateChart() {
    if (!chartRef) return;

    if (chart) chart.destroy();

    const labels = $testHistory.map(getReadableName);
    const data = $testHistory.map(result => {
      const { performance, security } = result;
      switch (selectedMetric) {
        case 'cipherTime': return performance?.cipherTime ?? 0;
        case 'cipherMemory': return performance?.cipherMemory ?? 0;
        case 'entropy': return security?.entropy ?? 0;
        case 'frequencyScore': return security?.frequencyScore ?? 0;
      }
    });

    chart = new Chart(chartRef, {
      type: 'bar',
      data: {
        labels,
        datasets: [{
          label: metricOptions.find(o => o.value === selectedMetric).label,
          data,
          backgroundColor: '#00ffff88',
          borderColor: '#00ffff',
          borderWidth: 1
        }]
      },
      options: {
        responsive: true,
        plugins: {
          legend: { display: true },
          tooltip: { mode: 'index' }
        },
        scales: {
          x: {
            ticks: {
              callback: (val, index) => labels[index]?.split(' ')[0] || ''
            }
          }
        }
      }
    });
  }

  onMount(() => {
    if (chartRef && $testHistory.length > 0) {
      updateChart();
    }
  });

  $: if (chartRef && $testHistory.length > 0 && selectedMetric) {
    updateChart();
  }
</script>

<main>
  <h1>Cryptographic Algorithms Analysis Tool</h1>
  <p>Test and benchmark various cryptographic algorithms with different parameters</p>

  <div class="panel">
    <h3>🕓 Tests History</h3>

    {#if $testHistory.length > 0}
      <div class="chart-controls">
        <label for="metric">Display metric:</label>
        <select id="metric" bind:value={selectedMetric}>
          {#each metricOptions as option}
            <option value={option.value}>{option.label}</option>
          {/each}
        </select>
      </div>

      <canvas bind:this={chartRef}></canvas>

      <ul>
        {#each $testHistory as result, index}
          <li>
            <strong>{index + 1}. Algorithm: {result.test.algorithm}</strong> <br />
            <small>Timestamp: {result.timestamp}</small><br />
            <small>Test ID: {result.id}</small><br />

            <details>
              <summary style="font-weight:600; margin-top:0.5rem;">View Details</summary>

              <div style="margin-top: 0.5rem;">
                <strong>Parameters:</strong>
                <ul>
                  {#each Object.entries(result.test.parameters || {}) as [key, value]}
                    <li><span class="param-key">{key}:</span> <span class="param-value">{value}</span></li>
                  {/each}
                </ul>

                {#if result.cipherText}
                  <strong>Cipher Text:</strong>
                  <pre class="mono">{result.cipherText}</pre>
                {/if}

                {#if result.decipherText}
                  <strong>Decryption Result:</strong>
                  <pre class="mono">{result.decipherText}</pre>
                {/if}

                <strong>Performance:</strong>
                <ul>
                  <li>Time: {result.performance?.cipherTime}</li>
                  <li>Memory: {result.performance?.cipherMemory}</li>
                </ul>

                <strong>Security:</strong>
                <ul>
                  <li>Entropy: {result.security?.entropy.toFixed(4)}</li>
                  <li>Frequency Score: {result.security?.frequencyScore.toFixed(4)}</li>
                </ul>
              </div>
            </details>
          </li>
        {/each}
      </ul>
    {:else}
      <p>No test history available.</p>
    {/if}

    <a href="/" class="history">← Back to Test</a>
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
    max-width: 800px;
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

  ul {
    list-style: none;
    padding: 0;
  }

  li {
    background: rgba(255, 255, 255, 0.1);
    padding: 1rem;
    border-radius: 0.6rem;
    margin-bottom: 1rem;
    word-break: break-word;
    white-space: normal;
    overflow-wrap: break-word;
  }

  canvas {
    margin: 2rem 0;
    max-width: 100%;
    background-color: #1e1e1e;
    border-radius: 1rem;
    padding: 1rem;
  }

  .chart-controls {
    margin-bottom: 1rem;
    display: flex;
    gap: 0.5rem;
    align-items: center;
  }

  select {
    padding: 0.5rem 1rem;
    border-radius: 0.5rem;
    border: none;
    background: #2c3e50;
    color: #00ffff;
    font-weight: bold;
  }

  select:focus {
    outline: none;
    box-shadow: 0 0 0 2px #00ffff55;
  }

  details > summary {
    list-style: none;
    cursor: pointer;
    position: relative;
    padding-left: 1.2rem;
  }

  details > summary::-webkit-details-marker {
    display: none;
  }

  details > summary::before {
    content: "▶";
    position: absolute;
    left: 0;
    top: 0;
    font-size: 1rem;
    line-height: 1;
    transition: transform 0.2s ease;
    color: #00ffff;
  }

  details[open] > summary::before {
    transform: rotate(90deg);
  }

  .mono {
    font-family: 'Courier New', monospace;
    background: rgba(0, 0, 0, 0.6);
    padding: 0.4rem 0.8rem;
    border-radius: 0.3rem;
    white-space: pre-wrap;
    overflow-x: auto;
  }

  .history {
    display: inline-block;
    margin-top: 2rem;
    padding: 0.7rem 1.2rem;
    background: #9b59b6;
    color: white;
    text-decoration: none;
    border-radius: 0.5rem;
    font-weight: 600;
    transition: filter 0.2s ease;
  }

  .history:hover {
    filter: brightness(1.1);
  }

  .param-key {
    font-weight: 600;
  }
</style>
