import adapter from '@sveltejs/adapter-static';
import {scss, sveltePreprocess} from 'svelte-preprocess';

/** @type {import('@sveltejs/kit').Config} */
const config = {
    preprocess: sveltePreprocess({
        scss: {

        }
    }),
    kit: {
        adapter: adapter({
            fallback: '404.html'
        }),
        paths: {
            base: process.argv.includes('dev') ? '' : process.env.BASE_PATH
        }
    }
};

export default config;
// If you're using a fallback (i.e. SPA mode) you don't need to prerender all
// pages by setting this here, but should prerender as many as possible to
// avoid large performance and SEO impacts
export const prerender = true;