import { sveltekit } from '@sveltejs/kit/vite';
import { defineConfig } from 'vite';
import adapter from '@sveltejs/adapter-static';
import { vitePreprocess } from '@sveltejs/vite-plugin-svelte';

export default defineConfig({
    plugins: [sveltekit({
        adapter: adapter({
            fallback: '404.html'
        }),
        paths: {
            base: ""
        },
        alias: { '$lib': 'src/lib' },
        preprocess: vitePreprocess({ script: true })
    })],
});

export const prerender = true;
