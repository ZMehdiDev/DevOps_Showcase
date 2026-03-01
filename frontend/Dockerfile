FROM nginx:1.27-alpine

ARG API_BASE_URL

COPY nginx.conf /etc/nginx/conf.d/default.conf
COPY index.html /usr/share/nginx/html/index.html
COPY styles.css /usr/share/nginx/html/styles.css

# Inject API base URL into JS at build time
COPY app.js /tmp/app.js
RUN sed "s|__API_BASE_URL__|${API_BASE_URL}|g" /tmp/app.js > /usr/share/nginx/html/app.js

EXPOSE 80