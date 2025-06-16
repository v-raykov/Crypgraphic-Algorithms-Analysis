<script>
  import { testHistory } from '$lib/stores.js';
</script>

<main>
  <h1>Cryptographic Algorithms Analysis Tool</h1>
  <p>Test and benchmark various cryptographic algorithms with different parameters</p>

  <div class="panel">
    <h3>🕓 Tests History</h3>

    {#if $testHistory.length > 0}
      <ul>
        {#each $testHistory as result, index}
          <li>
            <strong>{index + 1}. Algorithm: {result.test.algorithm}</strong> <br />
            <small>Timestamp: {result.timestamp}</small><br />
            <small>Test ID: {result.id}</small><br />

            <details>
              <summary style="font-weight:600; margin-top:0.5rem;">
                View Details
              </summary>

              <div style="margin-top: 0.5rem;">
                <strong>Parameters:</strong>
                <ul>
                  {#each Object.entries(result.test.parameters || {}) as [key, value]}
                    <li>{key}: {value}</li>
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
  }

  /* Hide default disclosure triangle */
  details > summary {
    list-style: none;
    cursor: pointer;
    position: relative;
    padding-left: 1.2rem; /* space for custom arrow */
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

  /* Rotate arrow when open */
  details[open] > summary::before {
    transform: rotate(90deg);
  }

  .mono {
    font-family: 'Courier New', monospace;
    background: rgba(0, 0, 0, 0.6);
    padding: 0.4rem 0.8rem;
    border-radius: 0.3rem;
    white-space: pre-wrap;
    word-break: break-word;
    margin-top: 0.3rem;
    margin-bottom: 0.7rem;
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
</style>
