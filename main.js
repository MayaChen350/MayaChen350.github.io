const socialMedias = ["GITHUB: MayaChen350", "DISCORD: giratina_shiny", "BLUESKY: giratina_shiny"];
const socialMediaPfps = ["images/PFP/github.jfif", "images/PFP/discord.webp", "images/PFP/bluesky.png"];

const mediaUsername = document.querySelector("#social-media-username");
const mediaPfp = document.querySelector("#pfp");
let indexMedia = 0;

const socialMediaChangeTimer = setInterval(() => {
  indexMedia = indexMedia !== socialMedias.length - 1 ? indexMedia + 1 : 0;
  mediaPfp.src = socialMediaPfps[indexMedia];
  mediaUsername.innerText = socialMedias[indexMedia];

  //   mediaPfp.style.opacity = 1;
  //   let gone = false;
  //   let timer = setInterval(() => {
  //     if (mediaPfp.style.opacity < 0.1 && !gone) {
  //       indexMedia = indexMedia !== socialMedias.length - 1 ? indexMedia + 1 : 0;
  //       mediaPfp.src = socialMediaPfps[indexMedia];
  //       gone = true;
  //     } else if (!gone) {
  //       mediaPfp.style.opacity -= 0.1;
  //     } else if (mediaPfp.style.opacity === 1) {
  //       mediaUsername.innerText = socialMedias[indexMedia];
  //       clearInterval(timer, socialMediaChangeTimer);
  //       gone = false;
  //     } else mediaPfp.style.opacity += 0.1;
  //   }, 100);
}, 10000);

// Persona 3 reload
// babybabybabybabybabybabybabybabybabybabybabybabybabybabybabybabybabybabybabybabybabybabybabybabybabybabybabybabybaby
const currentDate = new Date();
const p3rDate = new Date("2024/02/02 00:00:00");
const countdown = document.querySelector("#countdown");

countdown.innerText = Math.ceil((p3rDate - currentDate) / 60000);
