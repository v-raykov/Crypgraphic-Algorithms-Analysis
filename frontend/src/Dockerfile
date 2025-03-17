# Step 1: Build the frontend using Node.js
FROM node:18 AS build

WORKDIR /app

# Copy package.json and package-lock.json (or yarn.lock)
COPY package.json package-lock.json ./

# Install dependencies
RUN npm install

# Copy all the source files and build the app
COPY . .

# Build the SvelteKit project
RUN npm run build

# Step 2: Use Node.js to run the app
FROM node:18

WORKDIR /app

# Copy the built app from the previous step
COPY --from=build /app/build /app/build

# Install production dependencies
COPY package.json package-lock.json ./
RUN npm install --production

# Expose the application port (usually 3000)
EXPOSE 3000

# Start the app using Node.js
CMD ["node", "build"]
