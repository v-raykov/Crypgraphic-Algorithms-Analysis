# Use an official Node.js runtime as a base image
FROM node:18-alpine

# Set the working directory in the container
WORKDIR /app

# Copy package.json and package-lock.json first for better caching
COPY package*.json ./

# Install dependencies
RUN npm install

# Copy the rest of the application files
COPY . .

# Build the SvelteKit app
RUN npm run build

# Ensure production dependencies are installed
RUN npm ci --omit=dev

# Expose the port
EXPOSE 3000

# Use the correct command to run the built SvelteKit app
CMD ["node", "build/index.js"]
