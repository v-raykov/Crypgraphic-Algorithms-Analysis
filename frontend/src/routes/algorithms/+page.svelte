<!-- src/routes/example/+page.svelte -->
<script>
    import { onMount } from 'svelte';
    import { fetchAllAlgorithms, fetchAlgorithmByName, postTest } from '$lib/api.js';

    onMount(async () => {
        try {
            const result = await fetchAllAlgorithms();
            console.log('Algorithms list:', result);
        } catch (error) {
            console.error('Fetch failed:', error);
        }
        try {
            const result = await fetchAlgorithmByName('blowFish');
            console.log('Algorithm:', result);
        } catch (error) {
            console.error('Fetch failed:', error);
        }
        try {
            const result = await postTest({algorithm:"blowFish", plaintext:"text"});
            console.log('Algorithm:', result);
        } catch (error) {
            console.error('Fetch failed:', error);
        }
    });

   
</script>

<h1>Fetching data from backend... check the console</h1>
