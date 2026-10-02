import { sveltekit } from '@sveltejs/kit/vite';
import { defineConfig } from 'vite';
import adapter from '@sveltejs/adapter-static';

export default defineConfig({
    plugins: [sveltekit({
        adapter: adapter({
            fallback: '404.html'
        }),
        paths: {
            base: ""
        },
        alias: { '$lib': 'src/lib' }
    }
    )]
});

export const prerender = true;
