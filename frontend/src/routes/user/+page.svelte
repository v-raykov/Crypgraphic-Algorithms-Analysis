<script>
    import { onMount } from "svelte";

    onMount(() => {
        console.log("User page loaded!");
    });
    let message = "Hello";
  </script>
  
  <main>
    <h1>{message}</h1>
  </main>
  
  <style>
    main {
      display: flex;
      justify-content: center;
      align-items: center;
      height: 100vh;
      font-family: Arial, sans-serif;
    }
  </style>
  