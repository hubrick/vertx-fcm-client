/**
 * Copyright (C) 2017 Etaia AS (oss@hubrick.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.hubrick.vertx.fcm.client;

import com.hubrick.vertx.rest.RequestCacheOptions;
import io.vertx.core.http.Http2Settings;
import io.vertx.core.http.HttpClientOptions;
import io.vertx.core.http.HttpVersion;
import io.vertx.core.json.JsonObject;
import io.vertx.core.net.JdkSSLEngineOptions;
import io.vertx.core.net.JksOptions;
import io.vertx.core.net.KeyCertOptions;
import io.vertx.core.net.OpenSSLEngineOptions;
import io.vertx.core.net.PemKeyCertOptions;
import io.vertx.core.net.PemTrustOptions;
import io.vertx.core.net.PfxOptions;
import io.vertx.core.net.ProxyOptions;
import io.vertx.core.net.SSLEngineOptions;
import io.vertx.core.net.TrustOptions;

import java.util.List;

/**
 * @author marcus
 * @since 1.0.0
 */
public class FcmDeviceGroupClientOptions extends FcmClientOptions {

    private String fcmSenderId;

    public FcmDeviceGroupClientOptions() {}

    public FcmDeviceGroupClientOptions(final FcmDeviceGroupClientOptions other) {
        super(other);

        setFcmSenderId(other.getFcmSenderId());
    }

    public FcmDeviceGroupClientOptions(final HttpClientOptions other) {
        super(other);
    }

    public FcmDeviceGroupClientOptions(final JsonObject json) {
        super(json);

        setFcmSenderId(json.getString("fcmSenderId"));
    }

    public String getFcmSenderId() {
        return fcmSenderId;
    }

    public FcmDeviceGroupClientOptions setFcmSenderId(final String fcmSenderId) {
        this.fcmSenderId = fcmSenderId;
        return this;
    }

    @Override
    public FcmDeviceGroupClientOptions setFcmServerKey(final String fcmServerKey) {
        super.setFcmServerKey(fcmServerKey);
        return this;
    }

    @Override
    public FcmDeviceGroupClientOptions setHostnameOverride(final String hostnameOverride) {
        super.setHostnameOverride(hostnameOverride);
        return this;
    }

    @Override
    public FcmDeviceGroupClientOptions setGlobalTimeoutMs(final Long globalTimeoutMs) {
        super.setGlobalTimeoutMs(globalTimeoutMs);
        return this;
    }

    @Override
    public FcmDeviceGroupClientOptions setSendBufferSize(final int sendBufferSize) {
        super.setSendBufferSize(sendBufferSize);
        return this;
    }

    @Override
    public FcmDeviceGroupClientOptions setReceiveBufferSize(final int receiveBufferSize) {
        super.setReceiveBufferSize(receiveBufferSize);
        return this;
    }

    @Override
    public FcmDeviceGroupClientOptions setReuseAddress(final boolean reuseAddress) {
        super.setReuseAddress(reuseAddress);
        return this;
    }

    @Override
    public FcmDeviceGroupClientOptions setTrafficClass(final int trafficClass) {
        super.setTrafficClass(trafficClass);
        return this;
    }

    @Override
    public FcmDeviceGroupClientOptions setTcpNoDelay(final boolean tcpNoDelay) {
        super.setTcpNoDelay(tcpNoDelay);
        return this;
    }

    @Override
    public FcmDeviceGroupClientOptions setTcpKeepAlive(final boolean tcpKeepAlive) {
        super.setTcpKeepAlive(tcpKeepAlive);
        return this;
    }

    @Override
    public FcmDeviceGroupClientOptions setSoLinger(final int soLinger) {
        super.setSoLinger(soLinger);
        return this;
    }

    @Override
    public FcmDeviceGroupClientOptions setUsePooledBuffers(final boolean usePooledBuffers) {
        super.setUsePooledBuffers(usePooledBuffers);
        return this;
    }

    @Override
    public FcmDeviceGroupClientOptions setIdleTimeout(final int idleTimeout) {
        super.setIdleTimeout(idleTimeout);
        return this;
    }

    @Override
    public FcmDeviceGroupClientOptions setSsl(final boolean ssl) {
        super.setSsl(ssl);
        return this;
    }

    @Override
    public FcmDeviceGroupClientOptions setKeyCertOptions(final KeyCertOptions options) {
        super.setKeyCertOptions(options);
        return this;
    }

    @Override
    public FcmDeviceGroupClientOptions setKeyStoreOptions(final JksOptions options) {
        super.setKeyStoreOptions(options);
        return this;
    }

    @Override
    public FcmDeviceGroupClientOptions setPfxKeyCertOptions(final PfxOptions options) {
        super.setPfxKeyCertOptions(options);
        return this;
    }

    @Override
    public FcmDeviceGroupClientOptions setTrustOptions(final TrustOptions options) {
        super.setTrustOptions(options);
        return this;
    }

    @Override
    public FcmDeviceGroupClientOptions setPemKeyCertOptions(final PemKeyCertOptions options) {
        super.setPemKeyCertOptions(options);
        return this;
    }

    @Override
    public FcmDeviceGroupClientOptions setTrustStoreOptions(final JksOptions options) {
        super.setTrustStoreOptions(options);
        return this;
    }

    @Override
    public FcmDeviceGroupClientOptions setPfxTrustOptions(final PfxOptions options) {
        super.setPfxTrustOptions(options);
        return this;
    }

    @Override
    public FcmDeviceGroupClientOptions setPemTrustOptions(final PemTrustOptions options) {
        super.setPemTrustOptions(options);
        return this;
    }

    @Override
    public FcmDeviceGroupClientOptions setConnectTimeout(final int connectTimeout) {
        super.setConnectTimeout(connectTimeout);
        return this;
    }

    @Override
    public FcmDeviceGroupClientOptions setTrustAll(final boolean trustAll) {
        super.setTrustAll(trustAll);
        return this;
    }

    @Override
    public FcmDeviceGroupClientOptions setHttp2MultiplexingLimit(final int limit) {
        super.setHttp2MultiplexingLimit(limit);
        return this;
    }

    @Override
    public FcmDeviceGroupClientOptions setMaxPoolSize(final int maxPoolSize) {
        super.setMaxPoolSize(maxPoolSize);
        return this;
    }

    @Override
    public FcmDeviceGroupClientOptions setHttp2MaxPoolSize(final int max) {
        super.setHttp2MaxPoolSize(max);
        return this;
    }

    @Override
    public FcmDeviceGroupClientOptions setHttp2ConnectionWindowSize(final int http2ConnectionWindowSize) {
        super.setHttp2ConnectionWindowSize(http2ConnectionWindowSize);
        return this;
    }

    @Override
    public FcmDeviceGroupClientOptions setKeepAlive(final boolean keepAlive) {
        super.setKeepAlive(keepAlive);
        return this;
    }

    @Override
    public FcmDeviceGroupClientOptions setPipelining(final boolean pipelining) {
        super.setPipelining(pipelining);
        return this;
    }

    @Override
    public FcmDeviceGroupClientOptions setPipeliningLimit(final int limit) {
        super.setPipeliningLimit(limit);
        return this;
    }

    @Override
    public FcmDeviceGroupClientOptions setVerifyHost(final boolean verifyHost) {
        super.setVerifyHost(verifyHost);
        return this;
    }

    @Override
    public FcmDeviceGroupClientOptions setTryUseCompression(final boolean tryUseCompression) {
        super.setTryUseCompression(tryUseCompression);
        return this;
    }

    @Override
    public FcmDeviceGroupClientOptions setMaxWebsocketFrameSize(final int maxWebsocketFrameSize) {
        super.setMaxWebsocketFrameSize(maxWebsocketFrameSize);
        return this;
    }

    @Override
    public FcmDeviceGroupClientOptions setDefaultHost(final String defaultHost) {
        super.setDefaultHost(defaultHost);
        return this;
    }

    @Override
    public FcmDeviceGroupClientOptions setDefaultPort(final int defaultPort) {
        super.setDefaultPort(defaultPort);
        return this;
    }

    @Override
    public FcmDeviceGroupClientOptions setProtocolVersion(final HttpVersion protocolVersion) {
        super.setProtocolVersion(protocolVersion);
        return this;
    }

    @Override
    public FcmDeviceGroupClientOptions setMaxChunkSize(final int maxChunkSize) {
        super.setMaxChunkSize(maxChunkSize);
        return this;
    }

    @Override
    public FcmDeviceGroupClientOptions setMaxWaitQueueSize(final int maxWaitQueueSize) {
        super.setMaxWaitQueueSize(maxWaitQueueSize);
        return this;
    }

    @Override
    public FcmDeviceGroupClientOptions setInitialSettings(final Http2Settings settings) {
        super.setInitialSettings(settings);
        return this;
    }

    @Override
    public FcmDeviceGroupClientOptions setSslEngineOptions(final SSLEngineOptions sslEngineOptions) {
        super.setSslEngineOptions(sslEngineOptions);
        return this;
    }

    @Override
    public FcmDeviceGroupClientOptions setJdkSslEngineOptions(final JdkSSLEngineOptions sslEngineOptions) {
        super.setJdkSslEngineOptions(sslEngineOptions);
        return this;
    }

    @Override
    public FcmDeviceGroupClientOptions setOpenSslEngineOptions(final OpenSSLEngineOptions sslEngineOptions) {
        super.setOpenSslEngineOptions(sslEngineOptions);
        return this;
    }

    @Override
    public FcmDeviceGroupClientOptions setAlpnVersions(final List<HttpVersion> alpnVersions) {
        super.setAlpnVersions(alpnVersions);
        return this;
    }

    @Override
    public FcmDeviceGroupClientOptions setHttp2ClearTextUpgrade(final boolean value) {
        super.setHttp2ClearTextUpgrade(value);
        return this;
    }

    @Override
    public FcmDeviceGroupClientOptions setMetricsName(final String metricsName) {
        super.setMetricsName(metricsName);
        return this;
    }

    @Override
    public FcmDeviceGroupClientOptions setProxyOptions(final ProxyOptions proxyOptions) {
        super.setProxyOptions(proxyOptions);
        return this;
    }

    @Override
    public FcmDeviceGroupClientOptions setLogActivity(final boolean logEnabled) {
        super.setLogActivity(logEnabled);
        return this;
    }

    @Override
    public FcmDeviceGroupClientOptions setGlobalRequestTimeout(final long requestTimeoutInMillis) {
        super.setGlobalRequestTimeout(requestTimeoutInMillis);
        return this;
    }

    @Override
    public FcmDeviceGroupClientOptions setGlobalRequestCacheOptions(final RequestCacheOptions requestCacheOptions) {
        super.setGlobalRequestCacheOptions(requestCacheOptions);
        return this;
    }

    @Override
    public FcmDeviceGroupClientOptions setSendUnmaskedFrames(final boolean sendUnmaskedFrames) {
        super.setSendUnmaskedFrames(sendUnmaskedFrames);
        return this;
    }

    @Override
    public FcmDeviceGroupClientOptions setMaxWebsocketMessageSize(final int maxWebsocketMessageSize) {
        super.setMaxWebsocketMessageSize(maxWebsocketMessageSize);
        return this;
    }

    @Override
    public FcmDeviceGroupClientOptions setMaxInitialLineLength(final int maxInitialLineLength) {
        super.setMaxInitialLineLength(maxInitialLineLength);
        return this;
    }

    @Override
    public FcmDeviceGroupClientOptions setMaxHeaderSize(final int maxHeaderSize) {
        super.setMaxHeaderSize(maxHeaderSize);
        return this;
    }

    @Override
    public FcmDeviceGroupClientOptions setUseAlpn(final boolean useAlpn) {
        super.setUseAlpn(useAlpn);
        return this;
    }

    @Override
    public FcmDeviceGroupClientOptions setMaxRedirects(final int maxRedirects) {
        super.setMaxRedirects(maxRedirects);
        return this;
    }

    @Override
    public FcmDeviceGroupClientOptions setLocalAddress(final String localAddress) {
        super.setLocalAddress(localAddress);
        return this;
    }

    @Override
    public FcmDeviceGroupClientOptions setDecoderInitialBufferSize(final int decoderInitialBufferSize) {
        super.setDecoderInitialBufferSize(decoderInitialBufferSize);
        return this;
    }
}
